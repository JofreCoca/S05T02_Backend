package cat.itacademy.s05.t02.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idlikes;
    private int dogs_iddogs;
    private int dogs_iddogs_requested;
    private Status status;

}
