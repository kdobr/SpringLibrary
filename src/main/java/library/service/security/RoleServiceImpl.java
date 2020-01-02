package library.service.security;

import library.model.security.Role;
import library.repositories.security.RoleRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
