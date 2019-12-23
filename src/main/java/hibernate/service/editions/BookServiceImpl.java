package hibernate.service.editions;

import hibernate.dao.editions.BookDAO;
import hibernate.exeptions.BookExistsExceprion;
import hibernate.exeptions.BookNotExistsExceprion;
import hibernate.jpa_repository.editions.BookRepository;
import hibernate.model.editions.Book;
import hibernate.model.writers.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional
    public void addBook(String title) {
//        Optional<Book> bookOpt = bookDAO.findBookByTitle(title);
//        bookOpt.ifPresent(a -> {throw new BookExistsExceprion(title);});
//        bookDAO.addBook(title);
        Optional<Book> bookOpt = bookRepository.findByTitle(title);
        bookOpt.ifPresent(a -> {
            throw new BookExistsExceprion(title);
        });
        bookRepository.saveAndFlush(new Book(title));
    }


    @Override
    @Transactional
    public void deleteBook(String title) {
//        Optional<Book> bookOpt = bookDAO.findBookByTitle(title);
//        bookDAO.deleteBook(bookOpt.orElseThrow(() -> new BookNotExistsExceprion(title)));
        Optional<Book> bookOpt = bookRepository.findByTitle(title);
        bookRepository.delete(bookOpt.orElseThrow(() -> new BookNotExistsExceprion(title)));
    }

    @Override
    @Transactional
    public Book getBookByTitle(String title) {
        Optional<Book> bookOpt = bookDAO.findBookByTitle(title);
        return bookOpt.orElse(null);
    }

    @Override
    @Transactional
    public Book getBookById(int id) {
        return bookDAO.getBookById(id);
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    @Transactional
    public List<Author> getAllAuthorsOfBook(String title) {
        Optional<Book> bookCheck = bookDAO.findBookByTitle(title);
        Book book = bookCheck.orElseThrow(() -> new BookNotExistsExceprion(title));
        return book.getAuthorList();
    }

    @Override
    @Transactional
    public Book updateBook(String oldTitle, String newTitle) {
//        Optional<Book> bookCheck = bookDAO.findBookByTitle(oldTitle);
//        Book book = bookCheck.orElseThrow(() -> new BookNotExistsExceprion(oldTitle));
//        book.setTitle(newTitle);
//        return book;
        Optional<Book> bookOpt = bookRepository.findByTitle(oldTitle);
        Book book = bookOpt.orElseThrow(() -> new BookNotExistsExceprion(oldTitle));
        book.setTitle(newTitle);
        bookRepository.saveAndFlush(book);
        return book;
    }

//    @Override
//    public Optional<Book> findBookByTitle(String title, Session session) {
//        return Optional.empty();
//    }
}
