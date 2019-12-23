package hibernate.service.writers;

import hibernate.model.writers.Columnist;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface ColumnistService {

    public void addColumnist(String name);

    public void deleteColumnist(String name);

    public Columnist getColumnistByName(String name);

    public void addJournalToColumnist(String name, String title);

    public Columnist getColumnistById(int id);

    public List<Columnist> getAllColumnist();

    public void updateColumnist(String oldName, String newName);

}
