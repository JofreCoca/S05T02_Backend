package cat.itacademy.s05.t02.dtos;

public class AuthResponse {
    private String token;
    public AuthResponse(String token) {
        this.token = token;
    }
    public String getToken() { return token; }
}
