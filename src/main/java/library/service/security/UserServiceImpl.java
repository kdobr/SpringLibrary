package library.service.security;

import library.dto.editions.UserAddDto;
import library.dto.editions.UserDto;
import library.exeptions.RoleNotExistsException;
import library.exeptions.UserExistsException;
import library.exeptions.UserNotExistsException;
import library.model.security.User;
import library.repositories.security.RoleRepository;
import library.repositories.security.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder passwordEncoder;
    private UserRepository repository;
    private RoleRepository roleRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
        User user = repository.save(new User(username, passwordEncoder.encode(addDto.getPassword())));
        logger.info("we added user '{}' to lib with id: {}", username, user.getId());
        return user;
    }

    @Override
    @Transactional
    public UserDto addRoleToUser(int id, String role) {
        Optional<User> userOpt = repository.findById(id);
        User user = userOpt.orElseThrow(() -> new UserNotExistsException(" with id " + id));
        user.setRole(roleRepository.findByName(role).orElseThrow(() -> new RoleNotExistsException(role)));
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getRole().getName());
    }
}
