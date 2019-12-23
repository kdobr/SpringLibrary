package hibernate.dao.writers;

import hibernate.model.writers.Author;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public interface AuthorDAO {

    public void addAuthor(String name);

    public void deleteAuthor(Author author);

    public Author getAuthorById(int id);

    public List<Author> getAllAuthors();

    public Optional<Author> findAuthorByName(String name);
}
