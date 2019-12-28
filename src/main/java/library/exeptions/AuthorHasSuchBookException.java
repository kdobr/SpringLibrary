package library.exeptions;

public class AuthorHasSuchBookException extends RuntimeException  {

    public AuthorHasSuchBookException(String authorName, String bookTitle) {
        super("author '"+authorName+"' already has book: "+bookTitle);
    }
}
