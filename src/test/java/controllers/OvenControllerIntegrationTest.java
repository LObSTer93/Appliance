package controllers;

import config.DBConfig;
import config.RootConfig;
import enums.DoorStateEnum;
import enums.PowerStateEnum;
import enums.ProgramStateEnum;
import enums.StatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DBConfig.class, IntegrationConfig.class})
public class OvenControllerIntegrationTest {

    @Autowired
    private OvenController ovenController;

    @Test
    public void getStatuses() throws Exception {
        MockMvc mockMvc = standaloneSetup(ovenController).build();
        MvcResult result = mockMvc.perform(get("/ovenApi")).andReturn();
        String responseMessage = "[" +
                "{\"status\":\"" + StatusEnum.POWER.getName() + "\",\"state\":\"" + PowerStateEnum.OFF.getState() + "\"}," +
                "{\"status\":\"" + StatusEnum.DOOR.getName() + "\",\"state\":\"" + DoorStateEnum.CLOSED.getState() + "\"}," +
                "{\"status\":\"" + StatusEnum.PROGRAM.getName() + "\",\"state\":\"" + ProgramStateEnum.PIZZA.getState() + "\"}" +
                "]";
        assertEquals(result.getResponse().getContentAsString(), responseMessage);
    }
}