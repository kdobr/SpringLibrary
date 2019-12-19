package hibernate.rest;


import java.util.List;

import hibernate.dao.AuthorDAO;
import hibernate.dao.BookDAO;
import hibernate.dao.EditionDAO;
import hibernate.dao.JournalDAO;
import hibernate.model.editions.Book;
import hibernate.model.editions.Edition;
import hibernate.model.editions.Journal;
import hibernate.model.writers.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/edition")
public class EditionsRestController {


    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private JournalDAO journalDAO;
    @Autowired
    private EditionDAO editionDAO;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @GetMapping("/book")
    public Book getBookByTitle(@RequestParam String title) {
        return bookDAO.getBookByTitle(title);
    }

    @GetMapping("/journals")
    public List<Journal> getAllJournals() {
        return journalDAO.getAllJournals();
    }

    @GetMapping("/journal")
    public Journal getJournalByTitle(@RequestParam String title) {
        return journalDAO.getJournalByTitle(title);
    }

    @GetMapping("/editions")
    public List<Edition> getAllEditions() {
        return editionDAO.getAllEditions();
    }
}




	










