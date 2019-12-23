package hibernate.model.writers;

import hibernate.model.editions.Book;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue("Author")
@Getter
@Setter
@AllArgsConstructor
public class Author extends Writer {

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "auth_book",
            joinColumns = @JoinColumn(name = "auth_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    @Fetch(FetchMode.JOIN)
    private List<Book> bookList;

    public Author() {   }

    public Author(String name) {
        super(name);
    }

    public void addBook(Book book) {
        if (bookList == null) {
            bookList = new ArrayList<>();
        }
        bookList.add(book);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(getId(), author.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookList());
    }
}
