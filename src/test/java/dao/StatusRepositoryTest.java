package dao;

import config.DBConfig;
import config.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DBConfig.class)
public class StatusRepositoryTest {

    @Autowired
    private StatusRepository statusRepository;

    @Test
    public void edit() {
        String statusName = "Power";
        String newState = "On";
        String oldState = "Off";

        StatusDAO statusDAO = statusRepository.findByName(statusName);
        assertEquals(statusDAO.getState(), oldState);

        statusRepository.edit(statusName, newState);

        statusDAO = statusRepository.findByName(statusName);
        assertEquals(statusDAO.getState(), newState);
    }
}