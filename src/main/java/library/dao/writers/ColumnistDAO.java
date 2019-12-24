package library.dao.writers;

import library.model.writers.Columnist;

import java.util.List;
import java.util.Optional;

public interface ColumnistDAO {

    public void addColumnist(String name);

    public void deleteColumnist(Columnist columnist);

    public Columnist getColumnistById(int id);

    public List<Columnist> getAllColumnist();

    public Optional<Columnist> findColumnistByName(String name);

}
