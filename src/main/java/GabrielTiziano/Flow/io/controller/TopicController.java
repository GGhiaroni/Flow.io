package GabrielTiziano.Flow.io.controller;

import GabrielTiziano.Flow.io.model.Topic;
import GabrielTiziano.Flow.io.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topic")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Topic>> getTopics(){
        List<Topic> topicsListed = topicService.getTopics();
        return ResponseEntity.ok(topicsListed);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> getTopic(@PathVariable Long id){
        Topic topicFound = topicService.getTopic(id);
        if(topicFound != null) {
            return ResponseEntity.ok(topicFound);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar " +
                    "um tópico de id " + id + ".");
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<Topic> createTopic(Topic topic){
        Topic topicCreated = topicService.createTopic(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicCreated);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> updateTopic(@PathVariable Long id, @RequestBody Topic topicSent){
        Topic topicUpdated = topicService.updateTopic(id, topicSent);
        if (topicUpdated != null) {
            return ResponseEntity.ok("Tópico de id " + id + " atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível "
            + "encontrar um tópico de id " + id + ".");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deleteTopic(@PathVariable Long id){
       if(topicService.getTopic(id) != null){
           topicService.deleteTopic(id);
           return ResponseEntity.ok("Tópico de id " + id + " deletado com sucesso.");
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível " +
                   "encontrar um tópico de id " + id + ".");
       }
    }

}

