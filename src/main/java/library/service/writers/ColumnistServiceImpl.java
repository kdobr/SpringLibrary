package library.service.writers;

import library.exeptions.AuthorExistsExceprion;
import library.exeptions.AuthorNotExistsExceprion;
import library.exeptions.BookNotExistsExceprion;
import library.model.writers.Author;
import library.repositories.editions.JournalRepository;
import library.repositories.writers.ColumnistRepository;
import library.model.editions.Journal;
import library.model.writers.Columnist;
import library.service.editions.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ColumnistServiceImpl implements ColumnistService {

    @Autowired
    private ColumnistRepository columnistRepo;

    @Autowired
    JournalService journalService;

    @Autowired
    JournalRepository journalRepo;

    @Override
    @Transactional
    public void addColumnist(String name) {
        Optional<Columnist> columnistOpt = columnistRepo.findByName(name);
        columnistOpt.ifPresent(a -> {
            throw new AuthorExistsExceprion(name);
        });
        columnistRepo.save(new Columnist(name));
    }

    @Override
    @Transactional
    public void deleteColumnist(String name) {
        Optional<Columnist> columnistOpt = columnistRepo.findByName(name);
        columnistRepo.delete(columnistOpt.orElseThrow(() -> new AuthorNotExistsExceprion(name)));
    }

    @Override
    @Transactional
    public Columnist getColumnistByName(String name) {
        Optional<Columnist> columnistOpt = columnistRepo.findByName(name);
        return columnistOpt.orElseThrow(() -> new AuthorNotExistsExceprion(name));
    }

    @Override
    @Transactional
    public void addJournalToColumnist(String name, String title) {
        Optional<Columnist> columnistOpt = columnistRepo.findByName(name);
        Columnist columnist = columnistOpt.orElseThrow(() -> new AuthorNotExistsExceprion(name));
        Journal journal = journalRepo.findByTitle(title).orElseGet(() -> journalRepo.save(new Journal(title)));
        List<Journal> journalList = columnist.getJournalList();
        if (journalList.contains(journal)) {
            return;
        }
        journalList.add(journal);
    }

    @Override
    @Transactional
    public Columnist getColumnistById(int id) {
        return columnistRepo.findById(id).orElseThrow(() -> new AuthorNotExistsExceprion("with id " + id));
    }

    @Override
    @Transactional
    public List<Columnist> getAllColumnist() {
        return columnistRepo.findAll();
    }

    @Override
    @Transactional
    public Columnist updateColumnist(String oldName, String newName) {
        Optional<Columnist> columnistCheck = columnistRepo.findByName(oldName);
        Columnist columnist = columnistCheck.orElseThrow(() -> new AuthorNotExistsExceprion(oldName));
        columnist.setName(newName);
        return columnist;
    }
}
