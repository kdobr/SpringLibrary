package hibernate.dao;


import hibernate.exeptions.BookNotExistsExceprion;
import hibernate.model.editions.Book;
import hibernate.model.editions.Edition;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class EditionDAO {

    @Autowired
    private SessionFactory factory;

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
