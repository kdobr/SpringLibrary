package library.model.writers;

import library.model.editions.Journal;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue("Columnist")
@Setter
@Getter

public class Columnist extends Writer {


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "auth_book",
            joinColumns = @JoinColumn(name = "auth_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    @Fetch(FetchMode.JOIN)
    private List<Journal> journalList;

    public Columnist() {
    }

    public Columnist(String name) {
        super(name);
    }

    public void addJournal(Journal journal) {
        if (journalList == null) {
            journalList = new ArrayList<>();
        }
        journalList.add(journal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Columnist)) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public String toString() {
        return "Columnist{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                '}';
    }
}
