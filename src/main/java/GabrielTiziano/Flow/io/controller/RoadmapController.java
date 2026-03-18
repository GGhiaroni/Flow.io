package GabrielTiziano.Flow.io.controller;

import GabrielTiziano.Flow.io.service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RoadmapController {
    private final GeminiService geminiService;

    public RoadmapController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> studyRoadmapGenerated(){
        return geminiService.generateStudyRoadmap()
                .map(studyRoadmap -> ResponseEntity.ok(studyRoadmap))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
