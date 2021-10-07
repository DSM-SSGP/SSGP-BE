package project.ssgp.product.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.ssgp.product.entity.BrandEnum;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellingResponse {

    private BrandEnum brand;

    private String content;

    private int sellingPrice;

    private int price;

}
