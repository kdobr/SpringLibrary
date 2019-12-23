package hibernate.service.editions;

import hibernate.dao.editions.JournalDAO;
import hibernate.exeptions.BookExistsExceprion;
import hibernate.exeptions.BookNotExistsExceprion;
import hibernate.model.editions.Journal;
import hibernate.model.writers.Columnist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    JournalDAO journalDAO;

    @Override
    @Transactional
    public void addJournal(String title) {
        Optional<Journal> journalOpt = journalDAO.findJournalByTitle(title);
        journalOpt.ifPresent(a -> {throw new BookExistsExceprion(title);});
        journalDAO.addJournal(title);
    }


    @Override
    @Transactional
    public void deleteJournal(String title) {
        Optional<Journal> journalOpt = journalDAO.findJournalByTitle(title);
        journalDAO.deleteJournal(journalOpt.orElseThrow(() -> new BookNotExistsExceprion(title)));
    }

    @Override
    @Transactional
    public Journal getJournalByTitle(String title) {
        Optional<Journal> journalOpt = journalDAO.findJournalByTitle(title);
        return journalOpt.orElse(null);
    }

    @Override
    @Transactional
    public Journal getJournalById(int id) {
        return journalDAO.getJournalById(id);
    }

    @Override
    @Transactional
    public List<Journal> getAllJournals() {
        return journalDAO.getAllJournals();
    }

    @Override
    @Transactional
    public List<Columnist> getAllColumnistOfJournal(String title) {
        Optional<Journal> journalCheck = journalDAO.findJournalByTitle(title);
        Journal journal = journalCheck.orElseThrow(() -> new BookNotExistsExceprion(title));
        return journal.getColumnistList();
    }

    @Override
    @Transactional
    public Journal updateJournal(String oldTitle, String newTitle) {
        Optional<Journal> journalCheck = journalDAO.findJournalByTitle(oldTitle);
        Journal journal = journalCheck.orElseThrow(() -> new BookNotExistsExceprion(oldTitle));
        journal.setTitle(newTitle);
        return journal;
    }
}
