package library.service.editions;

import library.dto.editions.BookAddDto;
import library.dto.editions.BookForListDto;
import library.exeptions.BookExistsException;
import library.exeptions.BookNotExistsException;
import library.model.editions.Book;
import library.repositories.editions.BookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private BookRepository bookRepository;
    private ModelMapper mapper;

    @Override
    @Transactional
    public Book addBook(BookAddDto addDto) {
        String title = addDto.getTitle();
        logger.info("try to add book '{}'", title);
        bookRepository.findByTitle(title)
                .ifPresent(a -> {
                    logger.error("book '{}' already exists in lib. we can't add it", title);
                    throw new BookExistsException(title);
                });
        logger.info("ok. such book doesn't exist in lib - we adding...");
        Book book = bookRepository.save(new Book(title));
        logger.info("we added book '{}' to lib with id: {}", title, book.getId());
        return book;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteBookByTitle(String title) {
        logger.info("try to delete book '{}'", title);
        Optional<Book> bookOptional = bookRepository.findByTitle(title);
        bookRepository.delete(bookOptional.orElseThrow(() -> {
            logger.error("book '{}' does't exist in lib. we can't delete it", title);
            return new BookNotExistsException(title);
        }));
        logger.info("ok. book '{}' exist in lib. we deleting it...", title);
    }

    @Override
    @Transactional
    public void deleteBookById(int id) {
        logger.info("try to delete book with id: {}", id);
        Optional<Book> bookOpt = bookRepository.findById(id);
        bookRepository.delete(bookOpt.orElseThrow(() -> {
            logger.error("book with id: {} does't exist in lib. we can't delete it", id);
            return new BookNotExistsException("with id " + id);
        }));
        logger.info("ok. book with id: {} exist in lib. we deleting it...", id);
    }

    @Override
    @Transactional
    public Book getBookByTitle(String title) {
        logger.info("try to get book '{}'", title);
        Optional<Book> bookOpt = bookRepository.findByTitle(title);
        Book book = bookOpt.orElseThrow(() -> {
            logger.error("book '{}' does't exist in lib. we can't get it", title);
            return new BookNotExistsException(title);
        });
        logger.info("ok. book with title '{}' exist in lib. we getting it...", title);
        return book;
    }

    @Override
    @Transactional
    public Book getBookById(int id) {
        logger.info("try to get book with id: {}", id);
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            logger.error("book with id: {} does't exist in lib. we can't get it", id);
            return new BookNotExistsException("with id " + id);
        });
        logger.info("ok. book with id: {} exist in lib. we getting it...", id);
        return book;
    }

    @Override
    @Transactional
    public List<BookForListDto> getAllBooks(Pageable pageable) {

        logger.info("we try to get {} page with {} entries from all book list (without authors) from lib",
                pageable.getPageNumber(), pageable.getPageSize());
        Page<Book> books = bookRepository.findAll(pageable);
        logger.info("we've got {} page", pageable.getPageNumber());
        Page<BookForListDto> dtoBooks = books.map(b -> mapper.map(b, BookForListDto.class));
        logger.info("we've convert it to Dto");
        return dtoBooks.getContent();
    }

    @Override
    @Transactional
    public Book updateBook(String oldTitle, String newTitle) {
        logger.info("try to update book '{}' to '{}'", oldTitle, newTitle);
        Optional<Book> bookOpt = bookRepository.findByTitle(oldTitle);
        Book book = bookOpt.orElseThrow(() -> {
            logger.error("book '{}' does't exist in lib. we can't update it", oldTitle);
            return new BookNotExistsException(oldTitle);
        });
        logger.info("ok. book '{}' exist in lib. we updating it...", oldTitle);
        book.setTitle(newTitle);
        logger.info("book '{}' updated to title {}", oldTitle, newTitle);
        return book;
    }
}
