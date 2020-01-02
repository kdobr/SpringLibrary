package library.rest.writers;

import library.dto.editions.JournalAddDto;
import library.dto.writers.ColumnistAddDto;
import library.dto.writers.ColumnistDto;
import library.dto.writers.ColumnistForListDto;
import library.model.writers.Columnist;
import library.service.writers.ColumnistService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ColumnistRestController {

    private ColumnistService columnistService;
    private ModelMapper mapper;

    @PostMapping("/columnist")
    public ColumnistDto addColumnist(@RequestBody ColumnistAddDto addDto) {
        return convertToColumnistDto(columnistService.addColumnist(addDto));
    }

    @DeleteMapping("/columnist")
    public void deleteColumnistByName(@RequestParam String name) {
        columnistService.deleteColumnistByName(name);
    }

    @DeleteMapping("/columnist/{id}")
    public void deleteColumnistById(@PathVariable int id) {
        columnistService.deleteColumnistById(id);
    }

    @GetMapping("/columnists")
    public List<ColumnistForListDto> getAllColumnists() {
        return columnistService.getAllColumnist().stream()
                .map(a -> new ColumnistForListDto(a.getId(), a.getName())).collect(Collectors.toList());
    }

    @GetMapping("/columnist")
    public ColumnistDto getColumnistByName(@RequestParam String name) {
        return convertToColumnistDto(columnistService.getColumnistByName(name));
    }

    @GetMapping("/columnist/{id}")
    public ColumnistDto getColumnistById(@PathVariable int id) {
        return convertToColumnistDto(columnistService.getColumnistById(id));
    }

    @PutMapping("/columnist/{id}")
    public ColumnistDto addJournalToColumnist(@PathVariable int id, @RequestBody JournalAddDto addDto) {
        return convertToColumnistDto(columnistService.addJournalToColumnist(id, addDto));
    }

    @PutMapping("/columnist")
    public ColumnistDto updateColumnist(@RequestParam String oldName, String newName) {
        return convertToColumnistDto(columnistService.updateColumnist(oldName, newName));
    }

    private ColumnistDto convertToColumnistDto(Columnist columnist) {
        return mapper.map(columnist, ColumnistDto.class);
    }
}





	










