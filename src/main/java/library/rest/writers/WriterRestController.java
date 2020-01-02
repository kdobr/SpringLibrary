package library.rest.writers;


import library.dto.writers.WriterForListDto;
import library.service.writers.WriterService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class WriterRestController {

    private WriterService writerService;

    @GetMapping("/writers")
    public List<WriterForListDto> getAllWriters(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return writerService.getAllWriters(pageable);
    }
}





	










