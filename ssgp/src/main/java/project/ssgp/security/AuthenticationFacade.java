package project.ssgp.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import project.ssgp.entity.user.User;

@Component
public class AuthenticationFacade {

    public User getUser() {
        UserAuthentication authentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return authentication.getUser();
    }

}
