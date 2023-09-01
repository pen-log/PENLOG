package backend.global.config.jwt;

import backend.global.config.CustomUserDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private SecretKey secretKey;

    private final CustomUserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String generateToken(Map<String, Object> claims, int seconds) {
        long now = new Date().getTime();
        Date accessTokenExpiresIn = new Date(now + 1000L * seconds);

        return Jwts.builder()
                .claim("body", jsonToStr(claims))
                .setExpiration(accessTokenExpiresIn)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean verify(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey).build().parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public Map<String, Object> getClaims(String token) {
        String body = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody().get("body", String.class);
        return jsonToMap(body);
    }

    private Object jsonToStr(Map<String, Object> map) {
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    private Map<String, Object> jsonToMap(String jsonStr) {
        try {
            return new ObjectMapper().readValue(jsonStr, LinkedHashMap.class);
        } catch (JsonProcessingException e) {
            return Map.of();
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(
                Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject()
        );

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
