package project.ssgp.entity.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import project.ssgp.entity.product.Product;

import javax.persistence.Id;

@Document("tbl_selling")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Selling {

    @Id
    private Product product;

    private String content;

    private int price;

    private BrandEnum brand;

}
