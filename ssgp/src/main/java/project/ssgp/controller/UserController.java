package project.ssgp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import project.ssgp.entity.user.User;
import project.ssgp.payload.request.SignInRequest;
import project.ssgp.payload.request.SignUpRequest;
import project.ssgp.payload.request.UpdateInformationRequest;
import project.ssgp.payload.response.ProductResponse;
import project.ssgp.payload.response.TokenResponse;
import project.ssgp.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public void signUp(@RequestBody SignUpRequest signUpRequest) {

        userService.signUp(signUpRequest);

    }

    @PostMapping("/auth")
    public TokenResponse signIn(@RequestBody SignInRequest signInRequest) {

        return userService.signIn(signInRequest);

    }

    @PatchMapping("/user/password")
    public void updatePassword(@RequestBody UpdateInformationRequest updateInformationRequest) {

        userService.updatePassword(updateInformationRequest);

    }

    @GetMapping("/user/likelist")
    public List<ProductResponse> getLikeList() {
        return userService.getLikeList();
    }

}
