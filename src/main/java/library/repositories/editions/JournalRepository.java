package library.repositories.editions;

import library.model.editions.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Integer> {
    Optional<Journal> findByTitle(String title);
}
