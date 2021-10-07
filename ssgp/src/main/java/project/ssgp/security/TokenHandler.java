package project.ssgp.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import project.ssgp.user.repository.UserRepository;
import project.ssgp.util.JWTProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@AllArgsConstructor
public class TokenHandler implements HandlerInterceptor {

    private final UserRepository userRepository;
    private final JWTProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (response.getStatus() == 404) {
            throw new Exception();
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<?> handlerClass = handlerMethod.getBeanType();

        String token = jwtProvider.resolveToken(request);
        if(jwtProvider.validToken(token)){
            userRepository.findById(jwtProvider.parseToken(token))
                        .ifPresent(user -> SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(user)));

        }

        return true;

    }
}
