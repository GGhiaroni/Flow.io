package GabrielTiziano.Flow.io.service;

import GabrielTiziano.Flow.io.model.Topic;
import GabrielTiziano.Flow.io.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> getTopics(){
        return topicRepository.findAll();
    }

    public Topic createTopic(Topic topic){
        return topicRepository.save(topic);
    }
}
