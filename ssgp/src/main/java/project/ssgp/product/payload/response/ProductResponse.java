package project.ssgp.product.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private int ProductId;

    private String name;

    private List<String> brands;

    private List<SellingResponse> selling;

    private int price;

    private int likeCount;

    private String imagePath;

}
