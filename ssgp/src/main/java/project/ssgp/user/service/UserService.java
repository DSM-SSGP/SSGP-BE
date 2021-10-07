package project.ssgp.user.service;

import project.ssgp.user.payload.request.SignInRequest;
import project.ssgp.user.payload.request.SignUpRequest;
import project.ssgp.user.payload.request.UpdateInformationRequest;
import project.ssgp.product.payload.response.ProductResponse;
import project.ssgp.user.payload.response.TokenResponse;

import java.util.List;

public interface UserService {

    void signUp(SignUpRequest signUpRequest);

    TokenResponse signIn(SignInRequest signInRequest);

    void updatePassword(UpdateInformationRequest updateInformationRequest);

    List<ProductResponse> getLikeList();

}
