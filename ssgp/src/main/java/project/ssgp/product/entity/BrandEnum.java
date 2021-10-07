package project.ssgp.product.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BrandEnum {

    GS25(1, "GS25"),
    CU(2, "CU"),
    MINISTOP(3, "MIMISTOP"),
    SEVEN(4, "세븐일레븐"),
    EMART24(5, "emart24");

    private final int num;

    private final String name;
}
