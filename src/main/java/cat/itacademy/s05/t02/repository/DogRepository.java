package cat.itacademy.s05.t02.repository;

import cat.itacademy.s05.t02.model.Dog;
import cat.itacademy.s05.t02.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DogRepository extends JpaRepository<Dog, Integer> {
}
