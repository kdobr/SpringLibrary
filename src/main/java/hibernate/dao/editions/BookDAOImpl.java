package hibernate.dao.editions;


import hibernate.model.editions.Book;
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
public class BookDAOImpl implements BookDAO {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory factory;

    @Override
    public void addBook(String title) {
        try (Session session = factory.openSession()) {
            session.save(new Book(title));
        }
    }

    @Override
    @Transactional
        public void deleteBook(Book book) {
        try (Session session = factory.openSession()) {
            session.delete(book);
            System.out.println("delete" + book);

        }

    }

    @Override
       public Book getBookById(int id) {
        try (Session session = factory.openSession()) {
            return session.get(Book.class, id);
        }
    }

    @Override
       public List<Book> getAllBooks() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Book").getResultList();

        }
    }

    @Override
    @Transactional

       public Optional<Book> findBookByTitle(String title) {
        try (Session session = factory.openSession()) {
            String hql = "from Book where title = :title";
            Query query = session.createQuery(hql);
            query.setParameter("title", title);
            List<Book> tempList = query.getResultList();

            if (tempList.size() != 0) {
                return Optional.of(tempList.get(0));
            } else {
                return Optional.empty();
            }
        }
    }
}


//        public void updateBook (String oldTitle, String newTitle){
//            Transaction transaction = null;
//            try (Session session = factory.openSession()) {
//                transaction = session.beginTransaction();
//                Optional<Book> bookCheck = findBookByTitle(oldTitle, session);
//                Book book = bookCheck.orElseThrow(() -> new BookNotExistsExceprion(oldTitle));
//                book.setTitle(newTitle);
//                transaction.commit();
//            } catch (RuntimeException e) {
//                System.out.println(e.getMessage());
//                if (transaction != null) {
//                    transaction.rollback();
//                }
//            }
//        }
