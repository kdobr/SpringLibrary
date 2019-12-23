package hibernate.dao.writers;

import hibernate.dao.editions.JournalDAOImpl;
import hibernate.model.writers.Columnist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class ColumnistDAOImpl implements ColumnistDAO {

    @Autowired
    @Qualifier("sessionFactory")
    private  SessionFactory factory;
    @Autowired
    private JournalDAOImpl journalDAO;


    @Override
    public void addColumnist(String name) {
        try (Session session = factory.openSession()) {
            session.save(new Columnist(name));
        }
    }

    @Override
    public void deleteColumnist(Columnist columnist) {
        try (Session session = factory.openSession()) {
            session.delete(columnist);
        }
    }

    @Override
    public Columnist getColumnistById(int id) {
        try (Session session = factory.openSession()) {
            return session.get(Columnist.class, id);
        }
    }

    @Override
    public List<Columnist> getAllColumnist() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Columnist").getResultList();
        }
    }

    @Override
    public Optional<Columnist> findColumnistByName(String name) {
        try (Session session = factory.openSession()) {
            String hql = "from Columnist where name = :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            List<Columnist> tempList = query.getResultList();
            if (tempList.size() != 0) {
                return Optional.of(tempList.get(0));
            } else {
                return Optional.empty();
            }
        }
    }
}

