package cat.itacademy.s05.t02.model;

import jakarta.persistence.*;

@Entity
@Table(name= "DOGS")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddogs;
    private String name;
    private String breed;
    private int age;
    private Gender gender;
    private String photo_url;
    private int users_idusers;

    public int getIddogs() {
        return iddogs;
    }

    public void setIddogs(int iddogs) {
        this.iddogs = iddogs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public int getUsers_idusers() {
        return users_idusers;
    }

    public void setUsers_idusers(int users_idusers) {
        this.users_idusers = users_idusers;
    }
}
