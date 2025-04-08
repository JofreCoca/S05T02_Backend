package cat.itacademy.s05.t02.dtos;

import cat.itacademy.s05.t02.model.User;
import java.io.Serializable;

public class AuthResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private String jwttoken;

    public AuthResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}

