package hibernate.dao.writers;

import hibernate.dao.editions.BookDAO;
import hibernate.dao.editions.BookDAOImpl;
import hibernate.model.writers.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDAOImpl implements AuthorDAO {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory factory;

    @Autowired
    private BookDAO bookDAO;

    @Override
        public void addAuthor(String name) {
        try (Session session = factory.openSession()) {
            session.save(new Author(name));
        }
    }

    @Override
    public void deleteAuthor(Author author) {
        try (Session session = factory.openSession()) {
            session.delete(author);
        }
    }

    @Override
    public Author getAuthorById(int id) {
        try (Session session = factory.openSession()) {
            return session.get(Author.class, id);
        }
    }

    @Override
    public List<Author> getAllAuthors() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Author").getResultList();
        }
    }

    @Override
    public Optional<Author> findAuthorByName(String name) {
        try (Session session = factory.openSession()) {
            String hql = "from Author where name = :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            List<Author> tempList = query.getResultList();
            if (tempList.size() != 0) {
                return Optional.of(tempList.get(0));
            } else {
                return Optional.empty();
            }
        }
    }
}

//    public void updateAuthor(String oldName, String newName) {
//        try (Session session = factory.openSession()) {
//
//            Optional<Author> authorCheck = findAuthorByName(oldName);
//            Author author = authorCheck.orElseThrow(() -> new AuthorNotExistsExceprion(oldName));
//            author.setName(newName);
//        }
//    }

//    public void addBookToAuthor(String name, String title) {
//        try (Session session = factory.openSession()) {
//            Optional<Author> authorOpt = findAuthorByName(name);
//            Author author = authorOpt.orElseThrow(() -> new AuthorNotExistsExceprion(name));
//            bookDAO.addBook(title);
//            Book book = bookDAO.getBookByTitle(title);
//            author.getBookList().add(book);
//        }
//    }
