package library.dao.editions;


import library.model.editions.Journal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class JournalDAOImpl implements JournalDAO {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory factory;

    @Override
    public void addJournal(String title) {
        try (Session session = factory.openSession()) {
            session.save(new Journal(title));
        }
    }

    @Override
    public void deleteJournal(Journal journal) {
        try (Session session = factory.openSession()) {
            System.out.println(journal);
            session.delete(journal);
        }
    }


    @Override
    public Journal getJournalById(int id) {
        try (Session session = factory.openSession()) {
            return session.get(Journal.class, id);
        }
    }

    @Override
    public List<Journal> getAllJournals() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Journal").getResultList();

        }
    }

    @Override
    public Optional<Journal> findJournalByTitle(String title) {
        try (Session session = factory.openSession()) {
            String hql = "from Journal where title = :title";
            Query query = session.createQuery(hql);
            query.setParameter("title", title);
            List<Journal> tempList = query.getResultList();
            if (tempList.size() != 0) {
                return Optional.of(tempList.get(0));
            } else {
                return Optional.empty();
            }
        }
    }
}
