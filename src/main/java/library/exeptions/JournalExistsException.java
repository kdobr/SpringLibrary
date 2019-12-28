package library.exeptions;

public class JournalExistsException extends RuntimeException  {

    public JournalExistsException(String title) {
        super("Journal '"+ title+"' already exists");
    }
}
