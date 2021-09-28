package project.ssgp.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.ssgp.entity.user.User;
import project.ssgp.exception.UserAlreadyExistsException;
import project.ssgp.exception.UserNotFoundException;
import project.ssgp.payload.user.SignInRequest;
import project.ssgp.payload.user.SignUpRequest;
import project.ssgp.payload.user.TokenResponse;
import project.ssgp.repository.UserRepository;
import project.ssgp.util.JWTProvider;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

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

    private TokenResponse createTokenResponse(String id, String userType) {

        String accessToken = jwtProvider.getAccessToken(id, userType);
        String refreshToken = jwtProvider.getRefreshToken(id, userType);

        return new TokenResponse(accessToken, refreshToken);
    }
}
