package library.repositories.writers;

import library.model.writers.Writer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterRepository extends PagingAndSortingRepository<Writer, Integer> {
        Page<Writer> findAll(Pageable page);
}
