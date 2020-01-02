package library.dto.editions;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAddDto {

    private String username;
    private String password;

}
