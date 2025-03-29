package cat.itacademy.s05.t02.model;

import jakarta.persistence.*;

@Entity
@Table(name= "LIKES")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idlikes;
    private int dogs_iddogs;
    private int dogs_iddogs_requested;

}
