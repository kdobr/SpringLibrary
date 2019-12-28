package library.exeptions;

public class AuthorExistsException extends RuntimeException {

    public AuthorExistsException(String name) {
        super("author '" + name + "' already exists");
    }
}
