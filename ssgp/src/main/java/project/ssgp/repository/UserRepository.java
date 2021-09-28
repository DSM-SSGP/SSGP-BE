package project.ssgp.repository;

import org.springframework.data.repository.CrudRepository;
import project.ssgp.entity.user.User;

public interface UserRepository extends CrudRepository<User, String> {

}
