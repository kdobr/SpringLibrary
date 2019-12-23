package hibernate.rest;


import hibernate.dto.editions.*;
import hibernate.model.editions.Book;
import hibernate.model.editions.Journal;
import hibernate.service.editions.BookService;
import hibernate.service.editions.EditionService;
import hibernate.service.editions.JournalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/edition")
public class EditionsRestController {

    @Autowired
    private BookService bookService;
    @Autowired
    private JournalService journalService;
    @Autowired
    private EditionService editionService;
    @Autowired
    private ModelMapper mapper;

    @PostMapping("/book")
    public void addBook(@RequestParam String title) {
        bookService.addBook(title);
    }

    @PostMapping("/journal")
    public void addJournal(@RequestParam String title) {
        journalService.addJournal(title);
    }

    @DeleteMapping("/book")
    public void deleteBook(@RequestParam String title) {
        System.out.println("in controller");
        bookService.deleteBook(title);
    }

    @DeleteMapping("/journal")
    public void deleteJournal(@RequestParam String title) {
        journalService.deleteJournal(title);
    }

    @GetMapping("/books")
    public List<BookForListDto> getAllBooks() {
        return bookService.getAllBooks()
                .stream().map(b-> mapper.map(b, BookForListDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/book")
    public BookDto getBookByTitle(@RequestParam String title) {
        return convertToBookDto(bookService.getBookByTitle(title));
    }

    @GetMapping("/journal")
    public JournalDto getJournalByTitle(@RequestParam String title) {
        return convertToJournalDto(journalService.getJournalByTitle(title));
    }

    @GetMapping("/book/{id}")
    public BookDto getBookById(@PathVariable int id) {
        return convertToBookDto(bookService.getBookById(id));
    }

    @GetMapping("/journal/{id}")
    public JournalDto getJournalById(@PathVariable int id) {
        return convertToJournalDto(journalService.getJournalById(id));
    }

    @GetMapping("/journals")
    public List<JournalForListDto> getAllJournals() {
        return journalService.getAllJournals()
                .stream().map(j-> mapper.map(j, JournalForListDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/editions")
    public List<EditionForListDto> getAllEditions() {
        return editionService.getAllEditions()
                .stream().map(e-> mapper.map(e, EditionForListDto.class)).collect(Collectors.toList());
    }

    private BookDto convertToBookDto(Book book) {
        return mapper.map(book, BookDto.class);
    }
    private JournalDto convertToJournalDto(Journal journal) {
        return mapper.map(journal, JournalDto.class);
    }

    @PutMapping("/journal")
    public JournalDto updateJournal(@RequestParam String oldTitle, String newTitle) {
        return convertToJournalDto(journalService.updateJournal(oldTitle, newTitle));
    }
    @PutMapping("/book")
    public BookDto updateBook(@RequestParam String oldTitle, String newTitle) {
        return convertToBookDto(bookService.updateBook(oldTitle, newTitle));
    }

   }




	










