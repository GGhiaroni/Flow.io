package GabrielTiziano.Flow.io.repository;

import GabrielTiziano.Flow.io.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
}

