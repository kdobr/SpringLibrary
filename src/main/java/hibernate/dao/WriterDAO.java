package hibernate.dao;

import hibernate.model.writers.Writer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class WriterDAO {

    @Autowired
    private SessionFactory factory;

    public List<Writer> getAllWriters() {
        Session session = factory.openSession();
        String hql = "SELECT b FROM Writer b";
        Query query = session.createQuery(hql);
        List<Writer> list = query.getResultList();
       // System.out.println(list);
        session.close();
        return list;
    }
}
