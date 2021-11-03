package project.ssgp.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.ssgp.product.entity.BrandEnum;
import project.ssgp.product.payload.request.LikeRequest;
import project.ssgp.product.payload.response.ApplicationListResponse;
import project.ssgp.product.payload.response.ProductResponse;
import project.ssgp.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PutMapping("/{productId}")
    public void LikeProduct(@RequestBody LikeRequest likeRequest) {
        productService.LikeProduct(likeRequest);
    }

    @GetMapping("/{productId}")
    public ProductResponse getProduct(@PathVariable Integer productId) {
        return productService.getProduct(productId);
    }

    @GetMapping
    public List<ProductResponse> getNormalProductList() {
        return productService.getNormalProductList();
    }

    @GetMapping("/{brandEnum}")
    public List<ProductResponse> getBrandProductList(@RequestParam BrandEnum brand) {
        return productService.getBrandProductList(brand);
    }

    @GetMapping("/popular")
    public List<ProductResponse> getPopularProductList() {
        return productService.getPopularProductList();
    }

    @GetMapping("/recommend")
    public List<ProductResponse> getRecommendProductList() {
        return productService.getRecommendProductList();
    }

    @GetMapping("/lowPrice")
    public List<ProductResponse> getLowPriceProductList() {
        return productService.getLowPriceProductList();
    }

    @GetMapping("/search")
    public ApplicationListResponse searchProduct(@PathVariable String searchWord) {
        return productService.searchProduct(searchWord);
    }

}
