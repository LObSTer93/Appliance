package service;

import config.DBConfig;
import config.RootConfig;
import dto.StatusDTO;
import status_enums.PowerStateEnum;
import status_enums.StatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DBConfig.class})
public class StatusServiceImplIntegrationTest {

    @Autowired
    private StatusService statusService;

    @Test
    public void getStatuses(){
        List<StatusDTO> statuses = statusService.getStatuses();
        assertEquals(statuses.size(), 3);
    }

    @Test
    public void getStatus(){
        StatusDTO statusDTO = statusService.getStatus(StatusEnum.POWER.getValue());
        assertEquals(statusDTO.getState(), PowerStateEnum.OFF.getValue());
    }

    @Test
    public void editStatus(){
        StatusDTO statusDTO = statusService.getStatus(StatusEnum.POWER.getValue());
        assertEquals(statusDTO.getState(), PowerStateEnum.OFF.getValue());

        statusService.editStatus(StatusEnum.POWER.getValue(), PowerStateEnum.ON.getValue());

        statusDTO = statusService.getStatus(StatusEnum.POWER.getValue());
        assertEquals(statusDTO.getState(), PowerStateEnum.ON.getValue());
    }
}