package project.ssgp.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Selling {

    private String content;

    private int sellingPrice;

    private int price;

    private BrandEnum brand;

}