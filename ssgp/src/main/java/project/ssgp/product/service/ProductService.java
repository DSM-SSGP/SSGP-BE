package project.ssgp.product.service;

import project.ssgp.product.entity.BrandEnum;
import project.ssgp.product.payload.request.LikeRequest;
import project.ssgp.product.payload.response.ApplicationListResponse;
import project.ssgp.product.payload.response.ProductResponse;

import java.util.List;

public interface ProductService {

    void LikeProduct(LikeRequest likeRequest);

    ProductResponse getProduct(Integer productId);

    List<ProductResponse> getNormalProductList();

    List<ProductResponse> getBrandProductList(BrandEnum brand);

    List<ProductResponse> getPopularProductList();

    List<ProductResponse> getRecommendProductList();

    List<ProductResponse> getLowPriceProductList();

    ApplicationListResponse searchProduct(String searchWord);

}
