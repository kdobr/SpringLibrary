package library.service.writers;

import library.model.writers.Author;

import java.util.List;

public interface AuthorService {

    public void addAuthor(String name);

    public void deleteAuthor(String name);

    public Author getAuthorByName(String name);

    public void addBookToAuthor(String name, String title);

    public Author getAuthorById(int id);

    public List<Author> getAllAuthors();

    public Author updateAuthor(String oldName, String newName);

}
