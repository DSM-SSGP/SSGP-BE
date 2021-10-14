package project.ssgp.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.ssgp.product.entity.Product;
import project.ssgp.user.entity.User;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {

    List<Product> findAllByOrderByName();

    List<Product> findAllByOrderByLikeCountDesc();

    List<Product> findAllByLikeUserIdsContaining(String userId);

    List<Product> findAllByProductEnableTrue();

    List<Product> findAllByName(String productName);

    boolean existsByLikeUserIds(Product product, User user);

}
