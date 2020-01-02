package library.service.editions;

import library.dto.editions.EditionForListDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EditionService {
    List<EditionForListDto> getAllEditions(Pageable pageable);
}
