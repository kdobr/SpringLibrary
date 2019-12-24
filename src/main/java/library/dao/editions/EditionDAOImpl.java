package library.dao.editions;


import library.model.editions.Edition;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class EditionDAOImpl implements EditionDAO {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory factory;

    @Override
       public List<Edition> getAllEditions() {
        Session session = factory.openSession();
        String hql = "SELECT b FROM Edition b";
        Query query = session.createQuery(hql);
        List<Edition> listEditions = query.getResultList();
       // System.out.println(list);
        session.close();
        return listEditions;
    }
}
