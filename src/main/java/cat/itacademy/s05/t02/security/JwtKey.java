package cat.itacademy.s05.t02.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtKey {
    @Value("${PASSWORD_JWT}")
    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }
}
