package library.service.security;

import library.dto.editions.UserAddDto;
import library.dto.editions.UserDto;
import library.model.security.User;

public interface UserService {

    User addUser(UserAddDto addDto);

    UserDto addRoleToUser(int id, String role);
}
