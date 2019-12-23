package hibernate.dao.editions;

import hibernate.exeptions.AuthorExistsExceprion;
import hibernate.exeptions.BookNotExistsExceprion;
import hibernate.model.editions.Book;
import hibernate.model.writers.Author;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface BookDAO {

    public void addBook(String title);

    public void deleteBook(Book book);

    public Book getBookById(int id);

    public List<Book> getAllBooks();

    public Optional<Book> findBookByTitle(String title);
}
