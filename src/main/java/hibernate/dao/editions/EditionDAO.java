package hibernate.dao.editions;

import hibernate.model.editions.Edition;

import java.util.List;

public interface EditionDAO {
    public List<Edition> getAllEditions();
}
