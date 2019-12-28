package library.service.writers;

import library.dto.editions.BookAddDto;
import library.dto.editions.BookDto;
import library.dto.writers.AuthorAddDto;
import library.model.writers.Author;

import java.util.List;

public interface AuthorService {

    Author addAuthor(AuthorAddDto addDto);

    void deleteAuthorByName(String name);

    void deleteAuthorById(int id);

    Author getAuthorByName(String name);

    Author addBookToAuthor(int id, BookAddDto bookDto);

    Author getAuthorById(int id);

    List<Author> getAllAuthors();

    Author updateAuthor(String oldName, String newName);

}
