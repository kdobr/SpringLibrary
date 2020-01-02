package library.service.security;

import library.dto.editions.UserAddDto;
import library.exeptions.UserExistsException;
import library.exeptions.UserNotExistsException;
import library.model.security.Role;
import library.model.security.User;
import library.repositories.security.RoleRepository;
import library.repositories.security.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Override
    public Role findByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new RuntimeException(name));
    }
}
