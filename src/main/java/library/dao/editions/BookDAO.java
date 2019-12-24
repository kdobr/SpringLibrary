package library.dao.editions;

import library.model.editions.Book;

import java.util.List;
import java.util.Optional;

public interface BookDAO {

    public void addBook(String title);

    public void deleteBook(Book book);

    public Book getBookById(int id);

    public List<Book> getAllBooks();

    public Optional<Book> findBookByTitle(String title);
}
