package library.service.editions;

import library.dto.editions.EditionForListDto;
import library.model.editions.Edition;
import library.repositories.editions.EditionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EditionServiceImpl implements EditionService {

    private EditionRepository editionRepository;
    private ModelMapper mapper;

    @Override
    @Transactional
    public List<EditionForListDto> getAllEditions(Pageable pageable) {
        Page<Edition> resultPage = editionRepository.findAll(pageable);
        Page<EditionForListDto> dtoPage = resultPage.map(p -> mapper.map(p, EditionForListDto.class));
        return dtoPage.getContent();
    }
}
