package hibernate.dao;

import hibernate.exeptions.AuthorExistsExceprion;
import hibernate.exeptions.AuthorNotExistsExceprion;
import hibernate.model.editions.Book;
import hibernate.model.writers.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDAO {

    @Autowired
    private SessionFactory factory;

    @Autowired
    private BookDAO bookDAO;

    public void addAuthor(String name) {

        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Author> authorOpt = findAuthorByName(name, session);
            authorOpt.ifPresent(a -> {
                throw new AuthorExistsExceprion(name);
            });
            session.save(new Author(name));
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public void deleteAuthor(String name) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Author> authorOpt = findAuthorByName(name, session);
            session.delete(authorOpt.orElseThrow(() -> new AuthorNotExistsExceprion(name)));
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
        }
    }

    public Author getAuthorByName(String name) {
        Transaction transaction = null;
        Author author = null;
        try (Session session = factory.openSession()) {
            Optional<Author> authorOpt = findAuthorByName(name, session);
            author = authorOpt.orElseThrow(() -> new AuthorNotExistsExceprion(name));
            transaction = session.beginTransaction();
            int aTemp = author.getBookList().size();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
        return author;
    }

    public boolean addBookToAuthor(String name, String title) {
        Transaction transaction = null;
        Author author = null;
        try (Session session = factory.openSession()) {
            Optional<Author> authorOpt = findAuthorByName(name, session);
            author = authorOpt.orElseThrow(() -> new AuthorNotExistsExceprion(name));
            transaction = session.beginTransaction();
            bookDAO.addBook(title);
            Book book = bookDAO.getBookByTitle(title);
            author.getBookList().add(book);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return true;
    }

    public Author getAuthorById(int id) {
        Author author = null;
        Transaction transaction = null;
        try (Session session = factory.openSession()) {

            transaction = session.beginTransaction();
            author = session.get(Author.class, id);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return author;
    }

    public List<Author> getAllAuthors() {
        List<Author> authorList = new ArrayList<>();
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            authorList = session.createQuery("FROM Author").getResultList();
            transaction.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return authorList;
    }

    public void updateAuthor(String oldName, String newName) {

        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Author> authorCheck = findAuthorByName(oldName, session);
            Author author = authorCheck.orElseThrow(() -> new AuthorNotExistsExceprion(oldName));
            author.setName(newName);
            transaction.commit();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    private Optional<Author> findAuthorByName(String name, Session session) {

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

    private void printRollBackError() {
        System.err.println("Couldn’t roll back transaction");
    }

    private void printTransactionError() {
        System.err.println("Error occured, transaction rolled back");
    }
}
