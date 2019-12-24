package library.dao.writers;

import library.model.writers.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDAO {

    public void addAuthor(String name);

    public void deleteAuthor(Author author);

    public Author getAuthorById(int id);

    public List<Author> getAllAuthors();

    public Optional<Author> findAuthorByName(String name);
}
