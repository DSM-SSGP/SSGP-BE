package project.ssgp.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.ssgp.product.entity.ProductEntity;
import project.ssgp.product.entity.Selling;
import project.ssgp.product.payload.response.ProductResponse;
import project.ssgp.user.entity.UserEntity;
import project.ssgp.exception.UserAlreadyExistsException;
import project.ssgp.exception.UserNotFoundException;
import project.ssgp.user.payload.request.SignInRequest;
import project.ssgp.user.payload.request.SignUpRequest;
import project.ssgp.user.payload.request.UpdateInformationRequest;
import project.ssgp.user.payload.response.TokenResponse;
import project.ssgp.product.repository.ProductRepository;
import project.ssgp.user.repository.UserRepository;
import project.ssgp.security.AuthenticationFacade;
import project.ssgp.util.JWTProvider;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationFacade authenticationFacade;

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        userRepository.findById(signUpRequest.getId())
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException();
                });


        userRepository.save(
                UserEntity.builder()
                        .id(signUpRequest.getId())
                        .password(passwordEncoder.encode(signUpRequest.getPassword()))
                        .build()
        );
    }

    @Override
    public TokenResponse signIn(SignInRequest signInRequest) {

        String id = signInRequest.getId();
        String password = signInRequest.getPassword();

        return userRepository.findById(id)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(user -> createTokenResponse(user.getId(), "user"))
                .orElseThrow(UserNotFoundException::new);

    }

    @Override
    public void updatePassword(UpdateInformationRequest updateInformationRequest) {

        UserEntity user = authenticationFacade.getUser();
        String updatedPassword = updateInformationRequest.getPassword();

        userRepository.save(user.updatePassword(updatedPassword))
                .updatePassword(passwordEncoder.encode(updatedPassword));

    }

    @Override
    public List<ProductResponse> getLikeList() {

        UserEntity user = authenticationFacade.getUser();
        List<ProductEntity> products = productRepository.findAllByLikeUserIdsContaining(user.getId());

        List<ProductResponse> productResponses = new ArrayList<>();

        for(ProductEntity product : products){
            Integer price = product.getSellings().stream()
                    .map(Selling::getPrice)
                    .min(Integer::compareTo)
                    .orElse(0);
            productResponses.add(
                    ProductResponse.builder()
                            .ProductId(product.getId())
                            .name(product.getName())
                            .imagePath(product.getImagePath())
                            .price(price)
                            .likeCount(product.getLikeCount())
                            .build()
            );
        }

        return productResponses;
    }

    private TokenResponse createTokenResponse(String id, String userType) {

        String accessToken = jwtProvider.getAccessToken(id, userType);
        String refreshToken = jwtProvider.getRefreshToken(id, userType);

        return new TokenResponse(accessToken, refreshToken);
    }
}
