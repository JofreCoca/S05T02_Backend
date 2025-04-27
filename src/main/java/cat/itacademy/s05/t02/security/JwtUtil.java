package cat.itacademy.s05.t02.security;

import cat.itacademy.s05.t02.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public String generateToken(User user) {
        Map<String, String> claims = new HashMap<>();
        claims.put("idusers", Integer.toString(user.getId()));
        claims.put("role", user.getRole().toString());
        claims.put("email", user.getEmail());
        claims.put("photo_url", user.getPhoto_url());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractEmail(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public int extractIdUser(String token) {
        return Integer.parseInt(getClaims(token).get("idusers", String.class));
    }

    public String extractEmail(String token) {
        return getClaims(token).get("email", String.class);
    }


    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }


    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}

