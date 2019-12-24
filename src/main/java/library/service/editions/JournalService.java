package library.service.editions;

import library.model.editions.Journal;

import java.util.List;

public interface JournalService {

    public void addJournal(String title);

    public void deleteJournal(String title);

    public Journal getJournalByTitle(String title);

    public Journal getJournalById(int id);

    public List<Journal> getAllJournals() ;

    public Journal updateJournal(String oldTitle, String newTitle) ;


}
