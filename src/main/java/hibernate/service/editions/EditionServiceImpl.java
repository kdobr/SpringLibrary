package hibernate.service.editions;

import hibernate.dao.editions.EditionDAO;
import hibernate.model.editions.Edition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EditionServiceImpl implements EditionService {

    @Autowired
    EditionDAO editionDAO;

    @Override
    @Transactional
    public List<Edition> getAllEditions() {
        return editionDAO.getAllEditions();
    }
}
