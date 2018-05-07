package service;

import dao.StatusDAO;
import dao.StatusRepository;
import dto.StatusDTO;
import enums.PowerStateEnum;
import enums.StatusEnum;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class StatusServiceImplTest {

    @Test
    public void getStatuses(){
        StatusRepository statusRepository = Mockito.mock(StatusRepository.class);
        StatusDAO statusDAO = new StatusDAO();
        statusDAO.setId(1);
        statusDAO.setName(StatusEnum.POWER.getName());
        statusDAO.setState(PowerStateEnum.OFF.getState());
        when(statusRepository.findAll()).thenReturn(Collections.singletonList(statusDAO));
        StatusService statusService = new StatusServiceImpl(statusRepository);

        List<StatusDTO> statuses = statusService.getStatuses();
        StatusDTO statusDTO = statuses.get(0);
        assertEquals(statusDTO.getStatus(), StatusEnum.POWER.getName());
        assertEquals(statusDTO.getState(), PowerStateEnum.OFF.getState());
    }
}