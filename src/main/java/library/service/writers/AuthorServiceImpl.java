package library.service.writers;

import library.dto.editions.BookAddDto;
import library.dto.writers.AuthorAddDto;
import library.exeptions.AuthorExistsException;
import library.exeptions.AuthorHasSuchBookException;
import library.exeptions.AuthorNotExistsException;
import library.model.editions.Book;
import library.model.writers.Author;
import library.repositories.editions.BookRepository;
import library.repositories.writers.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Override
    @Transactional
    public Author addAuthor(AuthorAddDto addDto) {
        String name = addDto.getName();
        Optional<Author> authorOpt = authorRepository.findByName(name);
        authorOpt.ifPresent(a -> {
            throw new AuthorExistsException(name);
        });
        return authorRepository.save(new Author(name));
    }

    @Override
    @Transactional
    public void deleteAuthorByName(String name) {
        Optional<Author> authorOpt = authorRepository.findByName(name);
        authorRepository.delete(authorOpt.orElseThrow(() -> new AuthorNotExistsException(name)));
    }

    @Override
    @Transactional
    public void deleteAuthorById(int id) {
        Optional<Author> authorOpt = authorRepository.findById(id);
        authorRepository.delete(authorOpt.orElseThrow(() -> new AuthorNotExistsException(" with id " + id)));
    }

    @Override
    @Transactional
    public Author getAuthorByName(String name) {
        Optional<Author> authorOpt = authorRepository.findByName(name);
        return authorOpt.orElseThrow(() -> new AuthorNotExistsException(name));
    }

    @Override
    @Transactional
    public Author addBookToAuthor(int id, BookAddDto addDto) {
        Optional<Author> authorOpt = authorRepository.findById(id);
        Author author = authorOpt.orElseThrow(() -> new AuthorNotExistsException(" with id " + id));
        String title = addDto.getTitle();
        Book book = bookRepository.findByTitle(title).orElseGet(() ->
                bookRepository.save(Book.builder()
                        .title(title)
                        .build()));
        List<Book> bookList = author.getBookList();
        if (bookList.contains(book)) {
            throw new AuthorHasSuchBookException(author.getName(), book.getTitle());
        }
        bookList.add(book);
        return author;
    }

    @Override
    @Transactional
    public Author getAuthorById(int id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotExistsException("with id " + id));
    }

    @Override
    @Transactional
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public Author updateAuthor(String oldName, String newName) {
        Optional<Author> authorCheck = authorRepository.findByName(oldName);
        Author author = authorCheck.orElseThrow(() -> new AuthorNotExistsException(oldName));
        author.setName(newName);
        return author;
    }
}
