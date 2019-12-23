package hibernate.service.writers;

import hibernate.dao.writers.AuthorDAO;
import hibernate.dao.writers.AuthorDAOImpl;
import hibernate.exeptions.AuthorExistsExceprion;
import hibernate.exeptions.AuthorNotExistsExceprion;
import hibernate.model.editions.Book;
import hibernate.model.writers.Author;
import hibernate.service.editions.BookService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorDAO authorDAO;

    @Autowired
    BookService bookService;

    @Override
    @Transactional
    public void addAuthor(String name) {
        Optional<Author> authorOpt = authorDAO.findAuthorByName(name);
        authorOpt.ifPresent(a -> {
            throw new AuthorExistsExceprion(name);
        });
        authorDAO.addAuthor(name);
    }

    @Override
    @Transactional
    public void deleteAuthor(String name) {
        Optional<Author> authorOpt = authorDAO.findAuthorByName(name);
        authorDAO.deleteAuthor(authorOpt.orElseThrow(() -> new AuthorNotExistsExceprion(name)));
    }

    @Override
    @Transactional
    public Author getAuthorByName(String name) {
        Optional<Author> authorOpt = authorDAO.findAuthorByName(name);
        return authorOpt.orElse(null);
    }

    @Override
    @Transactional
    public void addBookToAuthor(String name, String title) {
        Optional<Author> authorOpt = authorDAO.findAuthorByName(name);
        Author author = authorOpt.orElseThrow(() -> new AuthorNotExistsExceprion(name));
        bookService.addBook(title);
        Book book = bookService.getBookByTitle(title);
        System.out.println(book);
        System.out.println(author.getBookList().size());
        author.getBookList().add(book);
        System.out.println(author.getBookList().size());
    }

    @Override
    @Transactional
    public Author getAuthorById(int id) {
        return authorDAO.getAuthorById(id);
    }

    @Override
    @Transactional
    public List<Author> getAllAuthors() {
        return authorDAO.getAllAuthors();
    }

    @Override
    @Transactional
    public void updateAuthor(String oldName, String newName) {
        Optional<Author> authorCheck = authorDAO.findAuthorByName(oldName);
        Author author = authorCheck.orElseThrow(() -> new AuthorNotExistsExceprion(oldName));
        author.setName(newName);
    }
}
