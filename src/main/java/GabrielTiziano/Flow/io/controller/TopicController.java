package GabrielTiziano.Flow.io.controller;

import GabrielTiziano.Flow.io.model.Topic;
import GabrielTiziano.Flow.io.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public ResponseEntity<List<Topic>> getTopics(){
        List<Topic> topicsListed = topicService.getTopics();
        return ResponseEntity.ok(topicsListed);
    }

    @PostMapping
    public ResponseEntity<Topic> createTopic(Topic topic){
        Topic topicCreated = topicService.createTopic(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicCreated);
    }

}

