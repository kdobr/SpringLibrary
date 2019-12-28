package library.exeptions;

public class ColumnistExistsException extends RuntimeException {

    public ColumnistExistsException(String name) {
        super("columnist '" + name + "' already exists");
    }
}
