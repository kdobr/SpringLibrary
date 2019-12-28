package library.exeptions;

public class JournalNotExistsException extends RuntimeException  {

    public JournalNotExistsException(String title) {
        super("Journal '"+ title+"' not exists");
    }
}
