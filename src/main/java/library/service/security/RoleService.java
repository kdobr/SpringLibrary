package library.service.security;

import library.dto.editions.UserAddDto;
import library.model.security.Role;
import library.model.security.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface RoleService  {

    Role findByName(String name);
}
