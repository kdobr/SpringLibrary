package library.rest.editions;


import library.dto.editions.EditionForListDto;
import library.service.editions.EditionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
public class EditionRestController {

    private EditionService editionService;

    @GetMapping("/editions")
    public List<EditionForListDto> getAllEditions(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return editionService.getAllEditions(pageable);
    }
}




	










