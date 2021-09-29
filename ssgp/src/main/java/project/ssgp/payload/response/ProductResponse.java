package project.ssgp.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private int ProductId;

    private String name;

    private String brand;

    private String selling;

    private int price;

    private String imagePath;

}
