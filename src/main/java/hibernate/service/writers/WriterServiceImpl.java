package hibernate.service.writers;

import hibernate.dao.writers.WriterDAOImpl;
import hibernate.model.writers.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterServiceImpl implements WriterService {

    @Autowired
    WriterDAOImpl writerDAO;

    @Override
    public List<Writer> getAllWriters() {
        return writerDAO.getAllWriters();
    }
}
