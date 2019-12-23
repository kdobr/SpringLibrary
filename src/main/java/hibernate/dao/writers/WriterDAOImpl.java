package hibernate.dao.writers;

import hibernate.model.writers.Writer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class WriterDAOImpl implements WriterDAO {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory factory;

    @Override
    public List<Writer> getAllWriters() {
        Session session = factory.openSession();
        String hql = "SELECT b FROM Writer b";
        Query query = session.createQuery(hql);
        List<Writer> list = query.getResultList();
        session.close();
        return list;
    }
}
