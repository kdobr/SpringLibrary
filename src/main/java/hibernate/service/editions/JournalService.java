package hibernate.service.editions;

import hibernate.model.editions.Journal;
import hibernate.model.writers.Columnist;

import java.util.List;

public interface JournalService {

    public void addJournal(String title);

    public void deleteJournal(String title);

    public Journal getJournalByTitle(String title);
    public Journal getJournalById(int id);

    public List<Journal> getAllJournals() ;

    public List<Columnist> getAllColumnistOfJournal(String title);

    public Journal updateJournal(String oldTitle, String newTitle) ;


}
