package project.ssgp.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.ssgp.product.entity.BrandEnum;
import project.ssgp.product.entity.Selling;

import java.util.List;

public interface SellingRepository extends MongoRepository<Selling, String> {

    List<Selling> findAllOrderByPriceDesc();

    List<Selling> findAllByBrand(BrandEnum brand);

}
