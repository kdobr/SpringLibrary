package hibernate.dto.writers;

import hibernate.model.editions.Journal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ColumnistForListDto {
    private int id;
    private String name;
}
