package library.exeptions;

public class BookExistsException extends RuntimeException  {

    public BookExistsException(String title) {
        super("book '"+ title+"' already exists");
    }
}
