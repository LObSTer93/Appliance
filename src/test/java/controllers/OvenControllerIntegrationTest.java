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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.annotation.PostConstruct;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static service.StatusServiceImpl.INCORRECT_STATE_MESSAGE;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DBConfig.class, IntegrationConfig.class})
public class OvenControllerIntegrationTest {

    @Autowired
    private OvenController ovenController;

    private MockMvc mockMvc;

    @PostConstruct
    public void init(){
        mockMvc = standaloneSetup(ovenController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void getStatuses() throws Exception {
        MvcResult result = mockMvc.perform(get("/ovenApi")).andReturn();
        String responseMessage = "[" +
                "{\"status\":\"" + StatusEnum.POWER.getValue() + "\",\"state\":\"" + PowerStateEnum.OFF.getValue() + "\"}," +
                "{\"status\":\"" + StatusEnum.DOOR.getValue() + "\",\"state\":\"" + DoorStateEnum.CLOSED.getValue() + "\"}," +
                "{\"status\":\"" + StatusEnum.PROGRAM.getValue() + "\",\"state\":\"" + ProgramStateEnum.PIZZA.getValue() + "\"}" +
                "]";
        assertEquals(result.getResponse().getContentAsString(), responseMessage);
    }

    @Test
    public void setStatus() throws Exception {
        String requestBody = "{\"status\": \"Power\", \"state\": \"Ot\"}";

        MockHttpServletResponse response = mockMvc.perform(put("/ovenApi")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        ).andReturn().getResponse();

        String responseBody = "{\"message\":\"" + INCORRECT_STATE_MESSAGE + "\"}";
        assertEquals(response.getContentAsString(), responseBody);
        assertEquals(response.getStatus(), HttpStatus.NOT_FOUND.value());
    }
}