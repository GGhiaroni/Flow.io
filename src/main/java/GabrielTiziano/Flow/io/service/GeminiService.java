package GabrielTiziano.Flow.io.service;

import GabrielTiziano.Flow.io.dto.TopicDTO;
import GabrielTiziano.Flow.io.model.Topic;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GeminiService {
    private final WebClient webClient;
    private String apiKey = System.getenv("API_KEY");

    public GeminiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generateStudyRoadmap(List<TopicDTO> topicList) {

        if (apiKey == null || apiKey.isBlank()) {
            return Mono.error(new RuntimeException("API Key não configurada."));
        }

        String cleanApiKey = apiKey.trim().replace("\"", "").replace("'", "");

        String fullUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + cleanApiKey;

        String topicsDb = topicList.stream()
                .map(topic -> "- " + topic.getName() + " (Categoria: " + topic.getCategory() + ")")
                .collect(Collectors.joining("\n"));

        String prompt = "Quero me torar um desenvolvedor " +
                "especialista e futuro tech lead com total domínio das habilidades que " +
                "o mercado de trabalho pede atualmente nas vagas. Crie um plano de estudo " +
                "detalhado, passo a passo, do básico ao domínio completo, considerando " +
                "os seguintes tópicos:\n " +
                topicsDb;

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        return webClient.post()
                .uri(java.net.URI.create(fullUrl))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(
                        status -> status.isError(),
                        response -> response.bodyToMono(String.class)
                                .defaultIfEmpty("Sem corpo de erro")
                                .flatMap(errorBody -> Mono.error(
                                        new RuntimeException("Erro na API Gemini: " + errorBody)
                                ))
                )

                .bodyToMono(String.class)
                .map(jsonString -> {
                    try {

                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode json = mapper.readTree(jsonString);

                        JsonNode textNode = json.path("candidates")
                                .get(0)
                                .path("content")
                                .path("parts")
                                .get(0)
                                .path("text");

                        if (textNode.isMissingNode() || textNode.asText().isBlank()) {
                            return "A API não retornou o texto do roadmap.";
                        }

                        return textNode.asText();

                    } catch (Exception e) {
                        return "Erro ao processar resposta da API do Gemini: " + e.getMessage();
                    }
                })
                .timeout(Duration.ofSeconds(60))
                .onErrorResume(error -> Mono.just("Erro ao gerar roadmap: " + error.getMessage()));
    }
}