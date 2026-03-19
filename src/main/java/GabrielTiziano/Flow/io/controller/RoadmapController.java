package GabrielTiziano.Flow.io.controller;

import GabrielTiziano.Flow.io.dto.TopicDTO;
import GabrielTiziano.Flow.io.model.Topic;
import GabrielTiziano.Flow.io.service.GeminiService;
import GabrielTiziano.Flow.io.service.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class RoadmapController {
    private final GeminiService geminiService;
    private final TopicService topicService;


    public RoadmapController(GeminiService geminiService, TopicService topicService) {
        this.geminiService = geminiService;
        this.topicService = topicService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> studyRoadmapGenerated(){
        List<TopicDTO> topicListDTO = topicService.getTopics();
        if(topicListDTO == null) {
            return Mono.just(ResponseEntity.badRequest().body("Nenhum tópico encontrado. Cadastre tópicos antes de gerar o roteiro."));
        }
        return geminiService.generateStudyRoadmap(topicListDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
