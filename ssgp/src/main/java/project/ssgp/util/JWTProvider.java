package project.ssgp.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import project.ssgp.exception.ExpiredTokenException;
import project.ssgp.exception.InvalidTokenException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Component
public class JWTProvider {
    @Value("${auth.jwt.secret}")
    private String SECURITY_KEY;

    private String generateToken(Object data, Long expire, String type, String userType){

        long nowMillis = System.currentTimeMillis();

        JwtBuilder builder = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(nowMillis))
                .setSubject(data.toString())
                .claim("userType", userType)
                .claim("type", type)
                .setExpiration(new Date(nowMillis + expire))
                .signWith(SignatureAlgorithm.HS256, SECURITY_KEY);

        return builder.compact();
    }

    public String getAccessToken(Object data, String userType) {
        return generateToken(data, 1000L * 3600 * 24, "access_token", userType);
    }

    public String getRefreshToken(Object data, String userType) {
        return generateToken(data, 1000L * 3600 * 24 * 30, "refresh_token", userType);
    }

    public String parseToken(String token) throws ExpiredJwtException {
        String result;
        try {
            Claims body = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();
            result = body.getSubject();
            if(!body.get("type").equals("access_token"))
                throw new InvalidTokenException();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (MalformedJwtException e) {
            throw new InvalidTokenException();
        }
        return result;
    }

    public String resolveToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if (token != null) return token.substring(7);
        return null;
    }

    public boolean validToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECURITY_KEY)
                    .parseClaimsJws(token).getBody().getSubject();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isUser(String token) {
        return Jwts.parser().setSigningKey(SECURITY_KEY)
                .parseClaimsJws(token).getBody().get("userType").equals("user");
    }
}
