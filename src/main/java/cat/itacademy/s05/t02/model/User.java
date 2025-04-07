package cat.itacademy.s05.t02.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name= "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idusers;
    private String email;
    private String password;
    private LocalDateTime registration_date;
    private String photo_url  = "";

    public int getId() {
        return idusers;
    }

    public void setId(int idusers) {
        this.idusers = idusers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(LocalDateTime registration_date) {
        this.registration_date = registration_date;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
