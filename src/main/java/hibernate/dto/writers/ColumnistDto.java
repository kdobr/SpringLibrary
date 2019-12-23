package hibernate.dto.writers;

import hibernate.dto.editions.BookForListDto;
import hibernate.dto.editions.JournalForListDto;
import hibernate.model.editions.Book;
import hibernate.model.editions.Journal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColumnistDto {
    private int id;
    private String name;
    private List<JournalForListDto> journalList;
}
