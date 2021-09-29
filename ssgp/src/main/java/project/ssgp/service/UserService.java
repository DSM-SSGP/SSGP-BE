package project.ssgp.service;

import project.ssgp.entity.user.User;
import project.ssgp.payload.request.SignInRequest;
import project.ssgp.payload.request.SignUpRequest;
import project.ssgp.payload.request.UpdateInformationRequest;
import project.ssgp.payload.response.ProductResponse;
import project.ssgp.payload.response.TokenResponse;

import java.util.List;

public interface UserService {

    void signUp(SignUpRequest signUpRequest);

    TokenResponse signIn(SignInRequest signInRequest);

    void updatePassword(UpdateInformationRequest updateInformationRequest);

    List<ProductResponse> getLikeList(User user);

}
