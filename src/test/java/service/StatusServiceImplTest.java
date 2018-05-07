package service;

import dao.StatusDAO;
import dao.StatusRepository;
import dto.StatusDTO;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class StatusServiceImplTest {

    @Test
    public void getSwitchStatus(){
        StatusRepository statusRepository = Mockito.mock(StatusRepository.class);
        StatusDAO statusDAO = new StatusDAO();
        statusDAO.setId(1);
        statusDAO.setName("switch");
        statusDAO.setState("off");
        when(statusRepository.getByName("switch")).thenReturn(statusDAO);
        StatusService statusService = new StatusServiceImpl(statusRepository);

        StatusDTO switchStatus = statusService.getSwitchStatus();
        assertEquals(switchStatus.getStatus(), "switch");
        assertEquals(switchStatus.getState(), "off");
    }
}