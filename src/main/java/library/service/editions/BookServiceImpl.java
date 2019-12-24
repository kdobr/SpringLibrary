package library.service.editions;

import library.exeptions.BookExistsExceprion;
import library.exeptions.BookNotExistsExceprion;
import library.repositories.editions.BookRepository;
import library.model.editions.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepo;

    @Override
    @Transactional
    public Book addBook(String title) {
        Optional<Book> bookOpt = bookRepo.findByTitle(title);
        bookOpt.ifPresent(a -> {
            throw new BookExistsExceprion(title);
        });
        return  bookRepo.save(new Book(title));
    }


    @Override
    @Transactional
    public void deleteBook(String title) {
        Optional<Book> bookOpt = bookRepo.findByTitle(title);
        bookRepo.delete(bookOpt.orElseThrow(() -> new BookNotExistsExceprion(title)));
    }

    @Override
    @Transactional
    public Book getBookByTitle(String title) {
        Optional<Book> bookOpt = bookRepo.findByTitle(title);
        return bookOpt.orElseThrow(() -> new BookNotExistsExceprion(title));
    }

    @Override
    @Transactional
    public Book getBookById(int id) {
        return bookRepo.findById(id).orElseThrow(() -> new BookNotExistsExceprion("with id " + id));

    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    @Transactional
    public Book updateBook(String oldTitle, String newTitle) {
        Optional<Book> bookOpt = bookRepo.findByTitle(oldTitle);
        Book book = bookOpt.orElseThrow(() -> new BookNotExistsExceprion(oldTitle));
        book.setTitle(newTitle);
        return book;
    }
}
