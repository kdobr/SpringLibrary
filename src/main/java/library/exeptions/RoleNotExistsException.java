package library.exeptions;

public class RoleNotExistsException extends RuntimeException {

    public RoleNotExistsException(String role) {
        super("role '" + role + "' not exists");
    }
}
