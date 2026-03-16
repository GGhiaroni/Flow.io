package GabrielTiziano.Flow.io.service;

import GabrielTiziano.Flow.io.dto.TopicDTO;
import GabrielTiziano.Flow.io.mapper.TopicMapper;
import GabrielTiziano.Flow.io.model.Topic;
import GabrielTiziano.Flow.io.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public TopicService(TopicRepository topicRepository, TopicMapper topicMapper) {
        this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
    }

    public List<TopicDTO> getTopics(){
        List<Topic> topicsFounded = topicRepository.findAll();
        return topicsFounded.stream().map(topicMapper::map).collect(Collectors.toList());
    }

    public TopicDTO createTopic(TopicDTO topicDTO){
        Topic topicModel = topicMapper.map(topicDTO);
        topicModel = topicRepository.save(topicModel);
        return topicMapper.map(topicModel);
    }

    public TopicDTO getTopic(Long id){
        Optional<Topic> topicFound = topicRepository.findById(id);
        return topicFound.map(topicMapper::map).orElse(null);
    }

    public TopicDTO updateTopic(Long id, TopicDTO topicSent){
        Optional<Topic> topicFound = topicRepository.findById(id);
        if(topicFound.isPresent()){
            Topic topicModel = topicMapper.map(topicSent);
            topicModel.setId(id);
            Topic topicUpdated = topicRepository.save(topicModel);
            return topicMapper.map(topicUpdated);
        } else {
            return null;
        }
    }

    public void deleteTopic(Long id){
        topicRepository.deleteById(id);
    }
}
