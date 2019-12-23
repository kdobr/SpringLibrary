package hibernate.jpa_repository.writers;

import hibernate.model.writers.Author;
import hibernate.model.writers.Columnist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnistRepository extends JpaRepository<Columnist, Integer> {
}
