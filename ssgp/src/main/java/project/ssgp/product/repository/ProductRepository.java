package project.ssgp.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.ssgp.product.entity.ProductEntity;
import project.ssgp.user.entity.UserEntity;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, Integer> {

    List<ProductEntity> findAllByOrderByName();

    List<ProductEntity> findAllByOrderByLikeCountDesc();

    List<ProductEntity> findAllByLikeUserIdsContaining(String userId);

    List<ProductEntity> findAllByProductEnableTrue();

    List<ProductEntity> findAllByName(String productName);

    boolean existsByLikeUserIds(ProductEntity product, UserEntity user);

}
