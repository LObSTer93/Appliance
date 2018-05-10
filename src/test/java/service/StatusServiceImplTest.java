package service;

import dao.StatusDAO;
import dao.StatusRepository;
import dto.StatusDTO;
import enums.PowerStateEnum;
import enums.StatusEnum;
import exceptions.ApiException;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static service.StatusServiceImpl.*;

public class StatusServiceImplTest {

    private final StatusRepository statusRepository = Mockito.mock(StatusRepository.class);
    private final StatusService statusService = new StatusServiceImpl(statusRepository);

    @Test
    public void getStatuses(){
        StatusDAO statusDAO = new StatusDAO();
        statusDAO.setId(1);
        statusDAO.setName(StatusEnum.POWER.getValue());
        statusDAO.setState(PowerStateEnum.OFF.getValue());
        when(statusRepository.findAll()).thenReturn(Collections.singletonList(statusDAO));

        List<StatusDTO> statuses = statusService.getStatuses();
        StatusDTO statusDTO = statuses.get(0);
        assertEquals(statusDTO.getStatus(), StatusEnum.POWER.getValue());
        assertEquals(statusDTO.getState(), PowerStateEnum.OFF.getValue());
    }

    @Test
    public void editStatus() {
        try {
            statusService.editStatus(null, "90");
        }catch (ApiException e){
            assertEquals(e.getMessage(), NO_STATUS_MESSAGE);
        }
        try {
            statusService.editStatus("Power", null);
        }catch (ApiException e){
            assertEquals(e.getMessage(), NO_STATE_MESSAGE);
        }
        try {
            statusService.editStatus("Powerr", "Ot");
        }catch (ApiException e){
            assertEquals(e.getMessage(), INCORRECT_STATUS_MESSAGE);
        }
        try {
            statusService.editStatus("Power", "Ot");
        }catch (ApiException e){
            assertEquals(e.getMessage(), INCORRECT_STATE_MESSAGE);
        }
    }
}