package library.service.editions;

import library.dto.editions.BookAddDto;
import library.dto.editions.BookForListDto;
import library.model.editions.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BookService {

    Book addBook(BookAddDto addDto);

    void deleteBookByTitle(String title);

    void deleteBookById(int id);

    Book getBookByTitle(String title);

    Book getBookById(int id);

    List<BookForListDto> getAllBooks(Pageable pageable);

    Book updateBook(String oldTitle, String newTitle);

}
