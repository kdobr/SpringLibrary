package library.service.editions;

import library.model.editions.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookService {

    public Book addBook(String title);

    public void deleteBook(String title);

    public Book getBookByTitle(String title);

    public Book getBookById(int id);

    public List<Book> getAllBooks();

    public Book updateBook(String oldTitle, String newTitle);





}
