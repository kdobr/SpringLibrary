package library.dao.editions;

import library.model.editions.Journal;

import java.util.List;
import java.util.Optional;

public interface JournalDAO {

    public void addJournal(String title);

    public void deleteJournal(Journal journal);

    public Journal getJournalById(int id);

    public List<Journal> getAllJournals();

    public Optional<Journal> findJournalByTitle(String title);
}
