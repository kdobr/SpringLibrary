package library.repositories.security;

import library.model.security.Role;
import library.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional <Role> findByName(String name);
}
