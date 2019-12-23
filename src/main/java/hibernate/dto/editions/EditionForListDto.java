package hibernate.dto.editions;

import hibernate.dto.writers.WriterForListDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EditionForListDto {
    private int id;
    private String title;
    }
