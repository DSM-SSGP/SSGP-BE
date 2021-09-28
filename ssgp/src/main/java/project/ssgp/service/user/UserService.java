package project.ssgp.service.user;

import project.ssgp.payload.user.SignInRequest;
import project.ssgp.payload.user.SignUpRequest;
import project.ssgp.payload.user.TokenResponse;

public interface UserService {

    void signUp(SignUpRequest signUpRequest);

    TokenResponse signIn(SignInRequest signInRequest);

}
