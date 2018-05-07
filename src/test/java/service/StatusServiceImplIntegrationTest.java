package service;

import config.DBConfig;
import config.RootConfig;
import dto.StatusDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DBConfig.class})
public class StatusServiceImplIntegrationTest {

    @Autowired
    private StatusService statusService;

    @Test
    public void getSwitchStatus(){
        StatusDTO switchStatus = statusService.getSwitchStatus();
        assertEquals(switchStatus.getStatus(), "switch");
        assertEquals(switchStatus.getState(), "off");
    }
}