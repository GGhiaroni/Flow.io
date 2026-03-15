package GabrielTiziano.Flow.io.mapper;

import GabrielTiziano.Flow.io.dto.TopicDTO;
import GabrielTiziano.Flow.io.model.Topic;
import org.springframework.stereotype.Component;

@Component
public class TopicMapper {
    public Topic map(TopicDTO topicDTO){
        Topic topic = new Topic();
        topic.setId(topicDTO.getId());
        topic.setName(topicDTO.getName());
        topic.setDescription(topicDTO.getDescription());
        topic.setCategory(topicDTO.getCategory());

        return topic;
    }

    public TopicDTO map(Topic topic){
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setId(topic.getId());
        topicDTO.setName(topic.getName());
        topicDTO.setDescription(topic.getDescription());
        topicDTO.setCategory(topic.getCategory());

        return topicDTO;
    }
}
