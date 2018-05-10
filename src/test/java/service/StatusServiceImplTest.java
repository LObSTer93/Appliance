package service;

import dao.StatusDAO;
import dao.StatusRepository;
import dto.StatusDTO;
import exceptions.ErrorMessageEnum;
import status_enums.PowerStateEnum;
import status_enums.StatusEnum;
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
        when(statusRepository.findAll()).thenReturn(Collections.singletonList(getStatusDAO()));

        List<StatusDTO> statuses = statusService.getStatuses();
        StatusDTO statusDTO = statuses.get(0);
        checkStatus(statusDTO);
    }

    @Test
    public void getStatus(){
        when(statusRepository.findByName(StatusEnum.POWER.getValue())).thenReturn(getStatusDAO());

        StatusDTO statusDTO = statusService.getStatus(StatusEnum.POWER.getValue());
        checkStatus(statusDTO);
    }

    @Test
    public void editStatus() {
        try {
            statusService.editStatus(null, "90");
        }catch (ApiException e){
            assertEquals(e.getMessage(), ErrorMessageEnum.NO_STATUS_MESSAGE.getMessage());
        }
        try {
            statusService.editStatus("Power", null);
        }catch (ApiException e){
            assertEquals(e.getMessage(), ErrorMessageEnum.NO_STATE_MESSAGE.getMessage());
        }
        try {
            statusService.editStatus("Powerr", "Ot");
        }catch (ApiException e){
            assertEquals(e.getMessage(), ErrorMessageEnum.INCORRECT_STATUS_MESSAGE.getMessage());
        }
        try {
            statusService.editStatus("Power", "Ot");
        }catch (ApiException e){
            assertEquals(e.getMessage(), ErrorMessageEnum.INCORRECT_STATE_MESSAGE.getMessage());
        }
    }

    private StatusDAO getStatusDAO(){
        StatusDAO statusDAO = new StatusDAO();
        statusDAO.setId(1);
        statusDAO.setName(StatusEnum.POWER.getValue());
        statusDAO.setState(PowerStateEnum.OFF.getValue());
        return statusDAO;
    }

    private void checkStatus(StatusDTO statusDTO){
        assertEquals(statusDTO.getStatus(), StatusEnum.POWER.getValue());
        assertEquals(statusDTO.getState(), PowerStateEnum.OFF.getValue());
    }
}