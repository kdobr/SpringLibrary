package library.service.editions;

import library.exeptions.BookExistsExceprion;
import library.exeptions.BookNotExistsExceprion;
import library.repositories.editions.JournalRepository;
import library.model.editions.Journal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository journalRepo;

    @Override
    @Transactional
    public void addJournal(String title) {
        Optional<Journal> journalOpt = journalRepo.findByTitle(title);
        journalOpt.ifPresent(a -> {
            throw new BookExistsExceprion(title);
        });
        journalRepo.save(new Journal(title));
    }


    @Override
    @Transactional
    public void deleteJournal(String title) {
        Optional<Journal> journalOpt = journalRepo.findByTitle(title);
        journalRepo.delete(journalOpt.orElseThrow(() -> new BookNotExistsExceprion(title)));
    }

    @Override
    @Transactional
    public Journal getJournalByTitle(String title) {
        Optional<Journal> journalOpt = journalRepo.findByTitle(title);
        return journalOpt.orElse(null);
    }

    @Override
    @Transactional
    public Journal getJournalById(int id) {
                return journalRepo.findById(id).orElseThrow(()->new BookNotExistsExceprion("with id "+id));
    }

    @Override
    @Transactional
    public List<Journal> getAllJournals() {
        return journalRepo.findAll();
    }

    @Override
    @Transactional
    public Journal updateJournal(String oldTitle, String newTitle) {
        Optional<Journal> journalCheck = journalRepo.findByTitle(oldTitle);
        Journal journal = journalCheck.orElseThrow(() -> new BookNotExistsExceprion(oldTitle));
        journal.setTitle(newTitle);
        return journal;
    }
}
