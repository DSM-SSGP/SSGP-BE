package project.ssgp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import project.ssgp.user.entity.UserEntity;
import project.ssgp.user.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class AuthenticationFacade {

    private final UserRepository userRepository;

    public UserEntity getUser() {
        return userRepository.findById(getUserId()).orElse(null);
    }

    private String getUserId() {
        return getAuthentication() != null ? (String) getAuthentication().getPrincipal() : "";
    }

    private static Authentication getAuthentication() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication();
    }

}
