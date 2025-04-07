package cat.itacademy.s05.t02.dtos;

import cat.itacademy.s05.t02.model.User;
import java.io.Serializable;

public class AuthResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private String jwttoken;
    private String username;

    public AuthResponse(String jwttoken, String username) {
        this.jwttoken = jwttoken;
        this.username = username;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public String getUsername() {
        return this.username;
    }
}

