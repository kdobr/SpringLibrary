package library.exeptions;

public class AuthorNotExistsException extends RuntimeException  {

    public AuthorNotExistsException(String name) {
        super("author '"+name+"' not exists");
    }
}
