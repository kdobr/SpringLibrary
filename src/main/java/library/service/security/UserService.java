package library.service.security;

import library.dto.editions.BookAddDto;
import library.dto.editions.UserAddDto;
import library.dto.editions.UserDto;
import library.model.security.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);
    User addUser(UserAddDto addDto);
    UserDto addRoleToUser (int id, String role);
}
