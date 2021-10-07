package project.ssgp.product.service;

import project.ssgp.product.entity.BrandEnum;
import project.ssgp.product.payload.response.ApplicationListResponse;
import project.ssgp.product.payload.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse getProduct(Integer productId);

    List<ProductResponse> getNormalProductList();

    List<ProductResponse> getBrandProductList(BrandEnum brand);

    List<ProductResponse> getPopularProductList();

    List<ProductResponse> getRecommendProductList();

    List<ProductResponse> getLowPriceProductList();

    List<ProductResponse> getProductFilterByBrandList();

    ApplicationListResponse searchProduct(String searchWord);

}
