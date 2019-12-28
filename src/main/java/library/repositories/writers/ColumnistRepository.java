package library.repositories.writers;

import library.model.writers.Columnist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColumnistRepository extends JpaRepository<Columnist, Integer> {
    Optional<Columnist> findByName(String name);
}
