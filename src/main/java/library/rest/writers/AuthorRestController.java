package library.rest.writers;


import library.dto.editions.BookAddDto;
import library.dto.writers.AuthorAddDto;
import library.dto.writers.AuthorDto;
import library.dto.writers.AuthorForListDto;
import library.model.writers.Author;
import library.service.writers.AuthorService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class AuthorRestController {

    private AuthorService authorService;
    private ModelMapper mapper;

    @PostMapping("/author")
    public AuthorDto addAuthor(@RequestBody AuthorAddDto addDto) {
        return convertToAuthorDto(authorService.addAuthor(addDto));
    }

    @DeleteMapping("/author")
    public void deleteAuthorByName(@RequestParam String name) {
        authorService.deleteAuthorByName(name);
    }

    @DeleteMapping("/author/{id}")
    public void deleteAuthorById(@PathVariable int id) {
        authorService.deleteAuthorById(id);
    }

    @GetMapping("/authors")
    public List<AuthorForListDto> getAllAuthors() {
        return authorService.getAllAuthors().stream()
                .map(a -> new AuthorForListDto(a.getId(), a.getName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/author")
    public AuthorDto getAuthorByName(@RequestParam String name) {
        return convertToAuthorDto(authorService.getAuthorByName(name));
    }

    @GetMapping("/author/{id}")
    public AuthorDto getAuthorById(@PathVariable int id) {
        return convertToAuthorDto(authorService.getAuthorById(id));
    }

    @PutMapping("/author/{id}")
    public AuthorDto addBookToAuthor(@PathVariable int id, @RequestBody BookAddDto bookDto) {
        return convertToAuthorDto(authorService.addBookToAuthor(id, bookDto));
    }

    @PutMapping("/author")
    public AuthorDto updateJournal(@RequestParam String oldName, String newName) {
        return convertToAuthorDto(authorService.updateAuthor(oldName, newName));
    }

    private AuthorDto convertToAuthorDto(Author author) {
        return mapper.map(author, AuthorDto.class);
    }
}





	










