package project.ssgp.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.ssgp.user.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
