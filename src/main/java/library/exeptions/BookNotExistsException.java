package library.exeptions;

public class BookNotExistsException extends RuntimeException  {

    public BookNotExistsException(String title) {
        super("book '"+ title+"' not exists");
    }
}
