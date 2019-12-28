package library.service.writers;

import library.dto.writers.WriterForListDto;
import library.model.writers.Writer;
import library.repositories.writers.WriterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WriterServiceImpl implements WriterService {

    private WriterRepository writerRepository;
    private ModelMapper mapper;

    @Override
    public List<WriterForListDto> getAllWriters(Pageable pageable) {
        Page<Writer> resultPage = writerRepository.findAll(pageable);
        Page<WriterForListDto> dtoPage = resultPage.map(p -> mapper.map(p, WriterForListDto.class));
        return dtoPage.getContent();
    }


}
