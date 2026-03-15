package GabrielTiziano.Flow.io.service;

import GabrielTiziano.Flow.io.model.Topic;
import GabrielTiziano.Flow.io.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Topic getTopic(Long id){
        Optional<Topic> topicFound = topicRepository.findById(id);
        return topicFound.orElse(null);
    }

    public Topic updateTopic(Long id, Topic topicSent){
        Optional<Topic> topicFound = topicRepository.findById(id);
        if(topicFound.isPresent()){
            topicSent.setId(id);
            topicRepository.save(topicSent);
        } else {
            return null;
        }
    }

    public void deleteTopic(Long id){
        topicRepository.deleteById(id);
    }
}
