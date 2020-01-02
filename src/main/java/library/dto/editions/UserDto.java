package library.dto.editions;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
private int id;
    private String username;
    private String password;
    private String role;

}
