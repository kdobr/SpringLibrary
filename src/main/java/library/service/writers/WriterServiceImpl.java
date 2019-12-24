package library.service.writers;

import library.dao.writers.WriterDAOImpl;
import library.model.writers.Writer;
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
