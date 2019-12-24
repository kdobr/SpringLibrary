package library.exeptions;

public class AuthorExistsExceprion extends RuntimeException  {

    public AuthorExistsExceprion(String name) {
        super("author '"+name+"' already exists");
    }
}
