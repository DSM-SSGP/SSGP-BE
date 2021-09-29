package project.ssgp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.ssgp.entity.user.User;

public interface UserRepository extends MongoRepository<User, String> {

}
