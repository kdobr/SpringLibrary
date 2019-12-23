package hibernate.rest;


import hibernate.dto.writers.*;
import hibernate.model.writers.Author;
import hibernate.model.writers.Columnist;
import hibernate.service.writers.AuthorService;
import hibernate.service.writers.ColumnistService;
import hibernate.service.writers.WriterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/writer")
public class WritersRestController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private ColumnistService columnistService;
    @Autowired
    private WriterService writerService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/author")
    public void addAuthor(@RequestParam String name) {
        authorService.addAuthor(name);
    }

    @PostMapping("/columnist")
    public void addColumnist(@RequestParam String name) {
        columnistService.addColumnist(name);
    }

    @DeleteMapping("/author")
    public void deleteAuthor(@RequestParam String name) {
        System.out.println("hello");
        authorService.deleteAuthor(name);
    }

    @DeleteMapping("/columnist")
    public void deleteColumnist(@RequestParam String name) {
        columnistService.deleteColumnist(name);
    }

    @GetMapping("/writers")
    public List<WriterForListDto> getAllWriters() {
        return writerService.getAllWriters().stream().map(a -> new WriterForListDto(a.getId(), a.getName())).collect(Collectors.toList());
    }

    @GetMapping("/authors")
    public List<AuthorForListDto> getAllAuthors() {
        return authorService.getAllAuthors().stream().map(a -> new AuthorForListDto(a.getId(), a.getName())).collect(Collectors.toList());
    }

    @GetMapping("/columnists")
    public List<ColumnistForListDto> getAllColumnists() {
        return columnistService.getAllColumnist().stream().map(a -> new ColumnistForListDto(a.getId(), a.getName())).collect(Collectors.toList());
    }

    @GetMapping("/author")
    public AuthorDto getAuthorByName(@RequestParam String name) {
        return convertToAuthorDto(authorService.getAuthorByName(name));
    }

    @GetMapping("/columnist")
    public ColumnistDto getColumnistByName(@RequestParam String name) {
        return convertToColumnistDto(columnistService.getColumnistByName(name));
    }

    @GetMapping("/author/{id}")
    public AuthorDto getAuthorById(@PathVariable int id) {
        return convertToAuthorDto(authorService.getAuthorById(id));
    }

    @GetMapping("/columnist/{id}")
    public ColumnistDto getColumnistById(@PathVariable int id) {
        return convertToColumnistDto(columnistService.getColumnistById(id));
    }

    @GetMapping("/columnist/add")
    public void addJournalToColumnist(@RequestParam String name, String title) {
        columnistService.addJournalToColumnist(name, title);
    }

    @GetMapping("/author/add")
    public void addBookToAuthor(@RequestParam String name, String title) {
        authorService.addBookToAuthor(name, title);
    }

    private AuthorDto convertToAuthorDto(Author author) {
        AuthorDto dto = mapper.map(author, AuthorDto.class);
        return dto;
    }

    private ColumnistDto convertToColumnistDto(Columnist columnist) {
        ColumnistDto dto = mapper.map(columnist, ColumnistDto.class);
        return dto;
    }
}




	










