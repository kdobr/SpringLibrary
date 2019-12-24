package library.service.writers;

import jdk.nashorn.internal.objects.annotations.Setter;
import library.exeptions.AuthorExistsExceprion;
import library.exeptions.AuthorNotExistsExceprion;
import library.exeptions.BookNotExistsExceprion;
import library.repositories.editions.BookRepository;
import library.repositories.writers.AuthorRepository;
import library.model.editions.Book;
import library.model.writers.Author;
import library.service.editions.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepo;

    @Override
    @Transactional
    public void addAuthor(String name) {
        Optional<Author> authorOpt = authorRepo.findByName(name);
        authorOpt.ifPresent(a -> {
            throw new AuthorExistsExceprion(name);
        });
        authorRepo.save(new Author(name));
    }

    @Override
    @Transactional
    public void deleteAuthor(String name) {
        Optional<Author> authorOpt = authorRepo.findByName(name);
        authorRepo.delete(authorOpt.orElseThrow(() -> new AuthorNotExistsExceprion(name)));
    }

    @Override
    @Transactional
    public Author getAuthorByName(String name) {
        Optional<Author> authorOpt = authorRepo.findByName(name);
        return authorOpt.orElseThrow(() -> new AuthorNotExistsExceprion(name));
    }

    @Override
    @Transactional
    public void addBookToAuthor(String name, String title) {
        Optional<Author> authorOpt = authorRepo.findByName(name);
        Author author = authorOpt.orElseThrow(() -> new AuthorNotExistsExceprion(name));
        Book book = bookRepo.findByTitle(title).orElseGet(() -> bookRepo.save(new Book(title)));
        List<Book> bookList = author.getBookList();
        if (bookList.contains(book)) {
            return;
        }
        bookList.add(book);
    }

    @Override
    @Transactional
    public Author getAuthorById(int id) {
        return authorRepo.findById(id).orElseThrow(() -> new AuthorNotExistsExceprion("with id " + id));
    }

    @Override
    @Transactional
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    @Override
    @Transactional
    public Author updateAuthor(String oldName, String newName) {
        Optional<Author> authorCheck = authorRepo.findByName(oldName);
        Author author = authorCheck.orElseThrow(() -> new AuthorNotExistsExceprion(oldName));
        author.setName(newName);
        return author;
    }
}
