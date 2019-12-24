package library.service.editions;

import library.repositories.editions.EditionRepository;
import library.model.editions.Edition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EditionServiceImpl implements EditionService {

    @Autowired
    private EditionRepository editionRepo;

    @Override
    @Transactional
    public List<Edition> getAllEditions() {
        return editionRepo.findAll();
    }
}
