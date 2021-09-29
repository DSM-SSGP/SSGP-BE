package project.ssgp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.ssgp.entity.like.Like;
import project.ssgp.entity.product.Product;
import project.ssgp.entity.product.Selling;
import project.ssgp.payload.response.ProductResponse;
import project.ssgp.repository.LikeRepository;
import project.ssgp.entity.user.User;
import project.ssgp.exception.UserAlreadyExistsException;
import project.ssgp.exception.UserNotFoundException;
import project.ssgp.payload.request.SignInRequest;
import project.ssgp.payload.request.SignUpRequest;
import project.ssgp.payload.request.UpdateInformationRequest;
import project.ssgp.payload.response.TokenResponse;
import project.ssgp.repository.UserRepository;
import project.ssgp.security.AuthenticationFacade;
import project.ssgp.util.JWTProvider;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
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
                User.builder()
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

        User user = authenticationFacade.getUser();
        String updatedPassword = updateInformationRequest.getPassword();

        userRepository.save(user.updatePassword(updatedPassword))
                .updatePassword(passwordEncoder.encode(updatedPassword));

    }

    @Override
    public List<ProductResponse> getLikeList() {

        User user = authenticationFacade.getUser();
        List<Like> likes = likeRepository.findAllByUser(user);

        List<ProductResponse> productResponses = new ArrayList<>();

        for(Like like : likes){
            Product product = like.getProduct();
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
