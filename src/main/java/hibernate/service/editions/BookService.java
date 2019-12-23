package hibernate.service.editions;

import hibernate.model.editions.Book;
import hibernate.model.writers.Author;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookService {

    public void addBook(String title);

    public void deleteBook(String title);

    public Book getBookByTitle(String title);

    public Book getBookById(int id);

    public List<Book> getAllBooks();

    public List<Author> getAllAuthorsOfBook(String title);

    public Book updateBook(String oldTitle, String newTitle);





}
