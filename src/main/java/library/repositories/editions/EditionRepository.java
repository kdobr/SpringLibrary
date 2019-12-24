package library.repositories.editions;

import library.model.editions.Edition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Integer> {

}
