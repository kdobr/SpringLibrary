package library.service.writers;

import library.dto.writers.WriterForListDto;
import library.model.writers.Writer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WriterService {

    List<WriterForListDto> getAllWriters(Pageable page);

}
