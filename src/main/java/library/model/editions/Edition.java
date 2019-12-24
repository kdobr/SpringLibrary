package library.model.editions;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name = "book")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "EDITION_TYPE")
public abstract class Edition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    public Edition(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edition)) return false;
        Edition edition = (Edition) o;
        return getId() == edition.getId() &&
                Objects.equals(getTitle(), edition.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }

    @Override
    public String toString() {
        return "Edition{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
