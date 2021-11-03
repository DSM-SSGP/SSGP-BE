package project.ssgp.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.ssgp.notification.payload.request.FcmRequest;
import project.ssgp.notification.service.NotificationService;
import project.ssgp.user.payload.request.SignInRequest;
import project.ssgp.user.payload.request.SignUpRequest;
import project.ssgp.user.payload.request.UpdateInformationRequest;
import project.ssgp.product.payload.response.ProductResponse;
import project.ssgp.user.payload.response.TokenResponse;
import project.ssgp.user.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final NotificationService notificationService;

    @PostMapping("/user")
    public void signUp(@RequestBody SignUpRequest signUpRequest) {

        userService.signUp(signUpRequest);

    }

    @PostMapping("/auth")
    public TokenResponse signIn(@RequestBody SignInRequest signInRequest) {

        return userService.signIn(signInRequest);

    }

    @PatchMapping("/{userId}/password")
    public void updatePassword(@RequestBody UpdateInformationRequest updateInformationRequest) {

        userService.updatePassword(updateInformationRequest);

    }

    @GetMapping("/{userId}/likelist")
    public List<ProductResponse> getLikeList() {
        return userService.getLikeList();
    }

    @PatchMapping("/{userId}")
    public void noticeSwitch(@RequestBody FcmRequest fcmRequest) {
        notificationService.noticeSwitch(fcmRequest);
    }

}
