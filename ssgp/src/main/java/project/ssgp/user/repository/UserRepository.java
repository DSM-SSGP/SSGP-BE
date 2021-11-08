package project.ssgp.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.ssgp.user.entity.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

}
