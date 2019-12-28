package library.exeptions;

public class ColumnistNotExistsException extends RuntimeException  {

    public ColumnistNotExistsException(String name) {
        super("columnist '"+name+"' not exists");
    }
}
