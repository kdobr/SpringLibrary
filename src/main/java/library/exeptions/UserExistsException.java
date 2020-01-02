package library.exeptions;

public class UserExistsException extends RuntimeException {

    public UserExistsException(String username) {
        super("user '" + username + "' already exists");
    }
}
