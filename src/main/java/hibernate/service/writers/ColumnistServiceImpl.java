package hibernate.service.writers;

import hibernate.dao.writers.ColumnistDAOImpl;
import hibernate.exeptions.AuthorExistsExceprion;
import hibernate.exeptions.AuthorNotExistsExceprion;
import hibernate.model.editions.Journal;
import hibernate.model.writers.Columnist;
import hibernate.service.editions.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ColumnistServiceImpl implements ColumnistService {

    @Autowired
    ColumnistDAOImpl columnistDAO;

    @Autowired
    JournalService journalService;

    @Override
    @Transactional
    public void addColumnist(String name) {
        Optional<Columnist> columnistOpt = columnistDAO.findColumnistByName(name);
        columnistOpt.ifPresent(a -> {
            throw new AuthorExistsExceprion(name);
        });
        columnistDAO.addColumnist(name);
    }

    @Override
    @Transactional
    public void deleteColumnist(String name) {
        Optional<Columnist> columnistOpt = columnistDAO.findColumnistByName(name);
        columnistDAO.deleteColumnist(columnistOpt.orElseThrow(() -> new AuthorNotExistsExceprion(name)));
    }

    @Override
    @Transactional
    public Columnist getColumnistByName(String name) {
        Optional<Columnist> columnistOpt = columnistDAO.findColumnistByName(name);
        return columnistOpt.orElse(null);
    }

    @Override
    @Transactional
    public void addJournalToColumnist(String name, String title) {
        Optional<Columnist> columnistOpt = columnistDAO.findColumnistByName(name);
        Columnist columnist = columnistOpt.orElseThrow(() -> new AuthorNotExistsExceprion(name));
        journalService.addJournal(title);
        Journal journal = journalService.getJournalByTitle(title);
        columnist.getJournalList().add(journal);
    }

    @Override
    @Transactional
    public Columnist getColumnistById(int id) {
        return columnistDAO.getColumnistById(id);
    }

    @Override
    @Transactional
    public List<Columnist> getAllColumnist() {
        return columnistDAO.getAllColumnist();
    }

    @Override
    @Transactional
    public void updateColumnist(String oldName, String newName) {
        Optional<Columnist> columnistCheck = columnistDAO.findColumnistByName(oldName);
        Columnist columnist = columnistCheck.orElseThrow(() -> new AuthorNotExistsExceprion(oldName));
        columnist.setName(newName);
    }
}
