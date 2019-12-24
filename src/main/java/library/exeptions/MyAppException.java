package library.exeptions;

public class MyAppException extends RuntimeException {
    public MyAppException() {
    }

    public MyAppException(String message) {
        super(message);
    }
}

