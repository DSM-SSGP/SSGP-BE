package project.ssgp.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.ssgp.exception.UserNotFoundException;
import project.ssgp.product.entity.BrandEnum;
import project.ssgp.product.entity.ProductEntity;
import project.ssgp.product.entity.Selling;
import project.ssgp.exception.ProductNotFoundException;
import project.ssgp.product.payload.request.LikeRequest;
import project.ssgp.product.payload.response.ApplicationListResponse;
import project.ssgp.product.payload.response.ProductResponse;
import project.ssgp.product.payload.response.SellingResponse;
import project.ssgp.product.repository.ProductRepository;
import project.ssgp.user.entity.UserEntity;
import project.ssgp.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public void LikeProduct(LikeRequest likeRequest) {

        UserEntity user = userRepository.findById(likeRequest.getUserId())
                .orElseThrow(UserNotFoundException::new);

        ProductEntity product = productRepository.findById(likeRequest.getProductId())
                .orElseThrow(ProductNotFoundException::new);

        boolean isLiked = productRepository.existsByLikeUserIds(product, user);

        if (isLiked) {
            product.removeLikeUser(user.getId());
        } else {
            product.addLikeUser(user.getId());
        }
        productRepository.save(product);
    }

    @Override
    public ProductResponse getProduct(Integer productId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        return ProductResponse.builder()
                .name(product.getName())
                .likeCount(product.getLikeCount())
                .imagePath(product.getImagePath())
                .selling(getSellingList(product))
                .build();
    }

    @Override
    public List<ProductResponse> getNormalProductList() {
        return getProductList(productRepository.findAllByOrderByName());
    }

    @Override
    public List<ProductResponse> getBrandProductList(BrandEnum brand) {
        return getProductList(productRepository.findAll().stream()
                .filter(product -> product.getSellings().stream()
                        .map(Selling::getBrand)
                        .collect(Collectors.toSet())
                        .contains(brand)
                ).collect(Collectors.toList()));
    }

    @Override
    public List<ProductResponse> getPopularProductList() {
        return getProductList(productRepository.findAllByOrderByLikeCountDesc());
    }

    @Override
    public List<ProductResponse> getRecommendProductList() { //추천 알고리즘 받아서 내용 넣기
        return null;
    }

    @Override
    public List<ProductResponse> getLowPriceProductList() { //최저가 순으로 정렬
        List<ProductEntity> products = productRepository.findAllByProductEnableTrue();
        products.sort(Comparator.comparingInt(o -> o.getSellings().stream().map(Selling::getPrice).min(Integer::compareTo).orElse(0)));
        return getProductList(products);
    }

    @Override
    public ApplicationListResponse searchProduct(String searchWord) {

        List<ProductEntity> productList = productRepository.findAllByName(searchWord);

        List<ProductResponse> productResponses = new ArrayList<>();

        for (ProductEntity product : productList) {
            Integer price = product.getSellings().stream()
                    .map(Selling::getPrice)
                    .min(Integer::compareTo)
                    .orElse(0);
            List<String> brands = product.getSellings().stream()
                    .map(selling -> selling.getBrand().getName())
                    .collect(Collectors.toList());
            productResponses.add(
                    ProductResponse.builder()
                            .ProductId(product.getId())
                            .name(product.getName())
                            .price(price)
                            .brands(brands)
                            .imagePath(product.getImagePath())
                            .build()
            );
        }

        return ApplicationListResponse.builder()
                .applicationResponses(productResponses)
                .build();
    }

    private List<ProductResponse> getProductList(List<ProductEntity> productList) {

        List<ProductResponse> productResponses = new ArrayList<>();

        for(ProductEntity product : productList) {
            Integer price = product.getSellings().stream()
                    .map(Selling::getPrice)
                    .min(Integer::compareTo)
                    .orElse(0);
            List<String> brands = product.getSellings().stream()
                    .map(selling -> selling.getBrand().getName())
                    .collect(Collectors.toList());
            productResponses.add(
                    ProductResponse.builder()
                            .ProductId(product.getId())
                            .name(product.getName())
                            .imagePath(product.getImagePath())
                            .price(price)
                            .brands(brands)
                            .build()

            );
        }

        return productResponses;

    }


    private List<SellingResponse> getSellingList(ProductEntity product) {
        List<SellingResponse> sellingResponses = new ArrayList<>();

        for (Selling selling : product.getSellings()) {
            sellingResponses.add(
                    SellingResponse.builder()
                            .brand(selling.getBrand())
                            .content(selling.getContent())
                            .sellingPrice(selling.getSellingPrice())
                            .price(selling.getPrice())
                            .build()
            );
        }

        return sellingResponses;
    }
}