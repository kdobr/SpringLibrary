package library.dto.writers;

import library.dto.editions.JournalForListDto;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class ColumnistDto {
    private int id;
    private String name;
    private List<JournalForListDto> journalList;
}
