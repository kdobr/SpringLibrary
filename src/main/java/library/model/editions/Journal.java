package library.model.editions;

import library.model.writers.Columnist;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue("Journal")
@Getter
@Setter


public class Journal extends Edition {

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "auth_book",
            joinColumns =@JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "auth_id"))
    @Fetch(FetchMode.JOIN)
    private List<Columnist> columnistList;

    public Journal() {   }

    @Builder
    public Journal(String title) {
        super(title);
    }

    public void addColumnist(Columnist columnist) {
        if (columnistList == null) {
            columnistList = new ArrayList<>();
        }
        columnistList.add(columnist);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Journal)) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public String toString() {
        return "Journal{" +
                "columnistList=" + columnistList +
                "} " + super.toString();
    }
}
