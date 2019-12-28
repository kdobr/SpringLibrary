package library.service.editions;

import library.dto.editions.JournalAddDto;
import library.exeptions.JournalExistsException;
import library.exeptions.JournalNotExistsException;
import library.model.editions.Journal;
import library.repositories.editions.JournalRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JournalServiceImpl implements JournalService {

    private static final Logger logger = LoggerFactory.getLogger(JournalServiceImpl.class);
    private JournalRepository journalRepository;

    @Override
    @Transactional
    public Journal addJournal(JournalAddDto addDto) {
        String title = addDto.getTitle();
        logger.info("try to add journal with title: {}", title);
        journalRepository.findByTitle(title)
                .ifPresent(a -> {
                    logger.error("journal {} already exists in lib. we can't add it", title);
                    throw new JournalExistsException(title);
                });
        logger.debug("ok. such journal doesn't exist in lib - we adding...");
        Journal journal = journalRepository.save(new Journal(title));
        logger.info("we added this journal to lib with id: {}", journal.getId());
        return journal;
    }

    @Override
    @Transactional
    public void deleteJournalByTitle(String title) {
        Optional<Journal> journalOpt = journalRepository.findByTitle(title);
        journalRepository.delete(journalOpt.orElseThrow(() -> new JournalNotExistsException(title)));
    }

    @Override
    @Transactional
    public void deleteJournalById(int id) {
        Optional<Journal> journalOpt = journalRepository.findById(id);
        journalRepository.delete(journalOpt.orElseThrow(() -> new JournalNotExistsException("with id " + id)));
    }

    @Override
    @Transactional
    public Journal getJournalByTitle(String title) {
        Optional<Journal> journalOpt = journalRepository.findByTitle(title);
        return journalOpt.orElse(null);
    }

    @Override
    @Transactional
    public Journal getJournalById(int id) {
        return journalRepository.findById(id).orElseThrow(() -> new JournalNotExistsException("with id " + id));
    }

    @Override
    @Transactional
    public List<Journal> getAllJournals() {
        return journalRepository.findAll();
    }

    @Override
    @Transactional
    public Journal updateJournal(String oldTitle, String newTitle) {
        Optional<Journal> journalCheck = journalRepository.findByTitle(oldTitle);
        Journal journal = journalCheck.orElseThrow(() -> new JournalNotExistsException(oldTitle));
        journal.setTitle(newTitle);
        return journal;
    }
}
