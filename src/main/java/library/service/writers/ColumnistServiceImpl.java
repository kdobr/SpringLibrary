package library.service.writers;

import library.dto.editions.JournalAddDto;
import library.dto.writers.ColumnistAddDto;
import library.exeptions.ColumnistExistsException;
import library.exeptions.ColumnistHasSuchJournalException;
import library.exeptions.ColumnistNotExistsException;
import library.model.editions.Journal;
import library.model.writers.Columnist;
import library.repositories.editions.JournalRepository;
import library.repositories.writers.ColumnistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ColumnistServiceImpl implements ColumnistService {

    private ColumnistRepository columnistRepository;
    private JournalRepository journalRepo;

    @Override
    @Transactional
    public Columnist addColumnist(ColumnistAddDto addDto) {
        String name = addDto.getName();
        Optional<Columnist> columnistOpt = columnistRepository.findByName(name);
        columnistOpt.ifPresent(a -> {
            throw new ColumnistExistsException(name);
        });
        return columnistRepository.save(new Columnist(name));
    }

    @Override
    @Transactional
    public void deleteColumnistByName(String name) {
        Optional<Columnist> columnistOpt = columnistRepository.findByName(name);
        columnistRepository.delete(columnistOpt.orElseThrow(() -> new ColumnistNotExistsException(name)));
    }

    @Override
    @Transactional
    public void deleteColumnistById(int id) {
        Optional<Columnist> columnistOpt = columnistRepository.findById(id);
        columnistRepository.delete(columnistOpt.orElseThrow(() -> new ColumnistNotExistsException(" with id " + id)));
    }

    @Override
    @Transactional
    public Columnist getColumnistByName(String name) {
        Optional<Columnist> columnistOpt = columnistRepository.findByName(name);
        return columnistOpt.orElseThrow(() -> new ColumnistNotExistsException(name));
    }

    @Override
    @Transactional
    public Columnist addJournalToColumnist(int id, JournalAddDto addDto) {
        Optional<Columnist> columnistOpt = columnistRepository.findById(id);
        String title = addDto.getTitle();
        Columnist columnist = columnistOpt.orElseThrow(() -> new ColumnistNotExistsException(" with id " + id));
        Journal journal = journalRepo.findByTitle(title).orElseGet(() -> journalRepo.save(Journal.builder()
                .title(title)
                .build()));
        List<Journal> journalList = columnist.getJournalList();
        if (journalList.contains(journal)) {
            throw new ColumnistHasSuchJournalException(columnist.getName(), title);
        }
        journalList.add(journal);
        return columnist;
    }

    @Override
    @Transactional
    public Columnist getColumnistById(int id) {
        return columnistRepository.findById(id).orElseThrow(() -> new ColumnistNotExistsException("with id " + id));
    }

    @Override
    @Transactional
    public List<Columnist> getAllColumnist() {
        return columnistRepository.findAll();
    }

    @Override
    @Transactional
    public Columnist updateColumnist(String oldName, String newName) {
        Optional<Columnist> columnistCheck = columnistRepository.findByName(oldName);
        Columnist columnist = columnistCheck.orElseThrow(() -> new ColumnistNotExistsException(oldName));
        columnist.setName(newName);
        return columnist;
    }
}
