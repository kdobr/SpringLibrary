package library.service.editions;

import library.dto.editions.JournalAddDto;
import library.model.editions.Journal;

import java.util.List;

public interface JournalService {

    Journal addJournal(JournalAddDto addDto);

    void deleteJournalByTitle(String title);

    void deleteJournalById(int id);

    Journal getJournalByTitle(String title);

    Journal getJournalById(int id);

    List<Journal> getAllJournals() ;

    Journal updateJournal(String oldTitle, String newTitle) ;

}

