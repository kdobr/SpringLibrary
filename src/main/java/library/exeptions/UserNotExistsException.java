package library.exeptions;

public class UserNotExistsException extends RuntimeException {

    public UserNotExistsException(String username) {
        super("user '" + username + "' not exists");
    }
}
