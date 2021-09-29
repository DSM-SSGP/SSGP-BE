package project.ssgp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.ssgp.entity.like.Like;
import project.ssgp.entity.user.User;

import java.util.List;

public interface LikeRepository extends MongoRepository<Like, Integer> {
    List<Like> findAllByUser(User user);
}
