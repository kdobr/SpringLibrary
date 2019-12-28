package library.service.writers;

import library.dto.editions.JournalAddDto;
import library.dto.writers.ColumnistAddDto;
import library.model.writers.Columnist;

import java.util.List;

public interface ColumnistService {

    Columnist addColumnist(ColumnistAddDto addDto);

    void deleteColumnistByName(String name);

    void deleteColumnistById(int id);

    Columnist getColumnistByName(String name);

    Columnist addJournalToColumnist(int id, JournalAddDto addDto);

    Columnist getColumnistById(int id);

    List<Columnist> getAllColumnist();

    Columnist updateColumnist(String oldName, String newName);

}
