package library.rest.security;


import library.dto.editions.RoleAddDto;
import library.dto.editions.UserAddDto;
import library.dto.editions.UserDto;
import library.model.security.User;
import library.service.security.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class UserRestController {

    private UserService userService;
    private ModelMapper mapper;

    @PostMapping("/user")
    public UserDto addUser(@RequestBody UserAddDto addDto) {
        return convertToUserDto(userService.addUser(addDto));
    }

    @PutMapping("/user/{id}")
    public UserDto addRoleToUser(@PathVariable int id, @RequestBody RoleAddDto roleDto) {
        return userService.addRoleToUser(id, roleDto.getName());
    }

    private UserDto convertToUserDto(User user) {
        return mapper.map(user, UserDto.class);
    }
}





	










