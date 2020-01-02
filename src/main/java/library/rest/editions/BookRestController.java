package library.rest.editions;


import library.dto.editions.BookAddDto;
import library.dto.editions.BookDto;
import library.dto.editions.BookForListDto;
import library.model.editions.Book;
import library.service.editions.BookService;
import library.service.editions.BookServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class BookRestController {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private BookService bookService;
    private ModelMapper mapper;

    @PostMapping("/book")
    public BookDto addBook(@RequestBody BookAddDto addDto) {
        return convertToBookDto(bookService.addBook(addDto));
    }

    @DeleteMapping("/book")
    public void deleteBookByTitle(@RequestParam String title) {
        bookService.deleteBookByTitle(title);
        logger.info("book with title '{}' deleted", title);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBookById(@PathVariable int id) {
        bookService.deleteBookById(id);
        logger.info("book with id: {} deleted", id);
    }

    @GetMapping("/book")
    public List<BookForListDto> getAllBooks(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        logger.info("made pageable with {} page, {} size, {} sort", page, size, sort);
        return bookService.getAllBooks(pageable);
    }

    @GetMapping("/book/title/{title}")
    public BookDto getBookByTitle(@PathVariable String title) {
        Book book = bookService.getBookByTitle(title);
        BookDto bookDto = convertToBookDto(book);
        logger.info("we've converted it to BookDto");
        return bookDto;
    }

    @GetMapping("/book/{id}")
    public BookDto getBookById(@PathVariable int id) {
        BookDto bookDto = convertToBookDto(bookService.getBookById(id));
        logger.info("we've converted it to BookDto");
        return bookDto;
    }

    @PutMapping("/book")
    public BookDto updateBook(@RequestParam String oldTitle, String newTitle) {
        return convertToBookDto(bookService.updateBook(oldTitle, newTitle));
    }

    private BookDto convertToBookDto(Book book) {
        return mapper.map(book, BookDto.class);
    }
}




	










