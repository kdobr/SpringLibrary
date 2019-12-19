package hibernate.rest;


import hibernate.dao.*;
import hibernate.dto.AuthorGetDto;
import hibernate.dto.ColumnistGetDto;
import hibernate.dto.WriterGetDto;
import hibernate.model.writers.Author;
import hibernate.model.writers.Columnist;
import hibernate.model.writers.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/writer")
public class WritersRestController {

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
	private ColumnistDAO columnistDAO;

    @Autowired
	private WriterDAO writerDAO;

	@PostMapping("/author")
	public void addAuthor(@RequestParam String name) {
		authorDAO.addAuthor(name);
	}

	@PostMapping("/columnist")
	public void addColumnist(@RequestParam String name) {
		columnistDAO.addColumnist(name);
	}

	@DeleteMapping("/author")
	public void deleteAuthor(@RequestParam String name) {
		authorDAO.deleteAuthor(name);
	}

	@DeleteMapping("/columnist")
	public void deleteColumnist(@RequestParam String name) {
		columnistDAO.deleteColumnist(name);
	}

	@GetMapping("/writers")
	public List<WriterGetDto> getAllWriters() {
		return writerDAO.getAllWriters().stream().map(a-> new WriterGetDto(a.getId(), a.getName())).collect(Collectors.toList());
	}

    @GetMapping("/authors")
    public List<AuthorGetDto> getAllAuthors() {
		return authorDAO.getAllAuthors().stream().map(a-> new AuthorGetDto(a.getId(), a.getName())).collect(Collectors.toList());
    }
    @GetMapping("/columnists")
    public List<ColumnistGetDto> getAllColumnists() {
		return columnistDAO.getAllColumnist().stream().map(a-> new ColumnistGetDto(a.getId(), a.getName())).collect(Collectors.toList());
    }

    @GetMapping("/author")
    public Author getAuthorByName(@RequestParam String name) {
        return authorDAO.getAuthorByName(name);
    }

	@GetMapping("/columnist")
	public Columnist getColumnistByName(@RequestParam String name) {
		return columnistDAO.getColumnistByName(name);
	}

	//use DTO, here it's more complicated then Author class
	@GetMapping("/author/{id}")
	public AuthorGetDto getAuthorById(@PathVariable int id) {
		Author author = authorDAO.getAuthorById(id);
		return new AuthorGetDto(author.getId(), author.getName());
	}

	//use standard class Columnist
	@GetMapping("/columnist/{id}")
	public Columnist getColumnistById(@PathVariable int id) {
		return columnistDAO.getColumnistById(id);
	}
}




	










