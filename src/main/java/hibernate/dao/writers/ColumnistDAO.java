package hibernate.dao.writers;

import hibernate.exeptions.AuthorExistsExceprion;
import hibernate.exeptions.AuthorNotExistsExceprion;
import hibernate.model.editions.Journal;
import hibernate.model.writers.Columnist;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ColumnistDAO {

    public void addColumnist(String name);

    public void deleteColumnist(Columnist columnist);

    public Columnist getColumnistById(int id);

    public List<Columnist> getAllColumnist();

    public Optional<Columnist> findColumnistByName(String name);

}
