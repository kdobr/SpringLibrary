package library.service.security;

import library.dto.editions.BookAddDto;
import library.dto.editions.UserAddDto;
import library.dto.editions.UserDto;
import library.exeptions.*;
import library.model.editions.Book;
import library.model.security.User;
import library.model.writers.Author;
import library.repositories.security.RoleRepository;
import library.repositories.security.UserRepository;
import library.service.editions.BookServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

   // private BCryptPasswordEncoder passwordEncoder;
    private UserRepository repository;
    private RoleRepository roleRepository;


    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User findByUserName(String userName) {
        return repository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException(userName));
    }

    @Override
    @Transactional
    public User addUser(UserAddDto addDto) {
        String username = addDto.getUsername();
        logger.info("try to add user '{}'", username);
        repository.findByUsername(username)
                .ifPresent(a -> {
                    logger.error("user '{}' already exists in lib. we can't add it", username);
                    throw new UserExistsException(username);
                });
        logger.info("ok. user doesn't exist in lib - we adding...");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = repository.save(new User(username, passwordEncoder.encode(addDto.getPassword())));
        logger.info("we added user '{}' to lib with id: {}", username, user.getId());
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                authorities);
    }

    @Override
    @Transactional
    public UserDto addRoleToUser(int id, String role) {
        Optional<User> userOpt = repository.findById(id);
        User user = userOpt.orElseThrow(() -> new UserNotExistsException(" with id " + id));
        user.setRole(roleRepository.findByName(role).orElseThrow(()->new RoleNotExistsException(role)));
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getRole().getName());
    }
}
