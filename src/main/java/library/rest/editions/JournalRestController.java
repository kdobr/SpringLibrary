package library.rest.editions;


import library.dto.editions.JournalAddDto;
import library.dto.editions.JournalDto;
import library.dto.editions.JournalForListDto;
import library.model.editions.Journal;
import library.service.editions.JournalService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
public class JournalRestController {

    private JournalService journalService;
    private ModelMapper mapper;

    @PostMapping("/journal")
    public JournalDto addJournal(@RequestBody JournalAddDto addDto) {
        return convertToJournalDto(journalService.addJournal(addDto));
    }

    @DeleteMapping("/journal")
    public void deleteJournalByTitle(@RequestParam String title) {
        journalService.deleteJournalByTitle(title);
    }

    @DeleteMapping("/journal/{id}")
    public void deleteJournalById(@PathVariable int id) {
        journalService.deleteJournalById(id);
    }

    @GetMapping("/journal")
    public JournalDto getJournalByTitle(@RequestParam String title) {
        return convertToJournalDto(journalService.getJournalByTitle(title));
    }

    @GetMapping("/journal/{id}")
    public JournalDto getJournalById(@PathVariable int id) {
        return convertToJournalDto(journalService.getJournalById(id));
    }

    @GetMapping("/journals")
    public List<JournalForListDto> getAllJournals() {
        return journalService.getAllJournals()
                .stream().map(j -> mapper.map(j, JournalForListDto.class)).collect(Collectors.toList());
    }

    @PutMapping("/journal")
    public JournalDto updateJournal(@RequestParam String oldTitle, String newTitle) {
        return convertToJournalDto(journalService.updateJournal(oldTitle, newTitle));
    }

    private JournalDto convertToJournalDto(Journal journal) {
        return mapper.map(journal, JournalDto.class);
    }
}




	










