package hibernate.jpa_repository.editions;

import hibernate.model.editions.Book;
import hibernate.model.editions.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Integer> {
}
