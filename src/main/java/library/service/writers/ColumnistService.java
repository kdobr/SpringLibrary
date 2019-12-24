package library.service.writers;

import library.model.writers.Columnist;

import java.util.List;

public interface ColumnistService {

    public void addColumnist(String name);

    public void deleteColumnist(String name);

    public Columnist getColumnistByName(String name);

    public void addJournalToColumnist(String name, String title);

    public Columnist getColumnistById(int id);

    public List<Columnist> getAllColumnist();

    public Columnist updateColumnist(String oldName, String newName);

}
