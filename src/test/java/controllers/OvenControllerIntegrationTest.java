package controllers;

import config.DBConfig;
import config.RootConfig;
import dto.StatusDTO;
import exceptions.ErrorMessageEnum;
import status_enums.DoorStateEnum;
import status_enums.PowerStateEnum;
import status_enums.ProgramStateEnum;
import status_enums.StatusEnum;
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
    public void getStatusSuccess() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/ovenApi/" + StatusEnum.POWER.getValue()))
                .andReturn()
                .getResponse();
        assertEquals(response.getContentAsString(), "{\"status\":\"" + StatusEnum.POWER.getValue() + "\",\"state\":\"" + PowerStateEnum.OFF.getValue() + "\"}");
    }

    @Test
    public void getStatusError() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/ovenApi/Temperature"))
                .andReturn()
                .getResponse();

        String responseBody = "{\"message\":\"" + ErrorMessageEnum.INCORRECT_STATUS_MESSAGE.getMessage() + "\"}";
        assertEquals(response.getContentAsString(), responseBody);
        assertEquals(response.getStatus(), HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void setStatusError() throws Exception {
        String requestBody = "{\"status\": \"" + StatusEnum.POWER.getValue() + "\", \"state\": \"Ot\"}";

        MockHttpServletResponse response = mockMvc.perform(put("/ovenApi")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        ).andReturn().getResponse();

        String responseBody = "{\"message\":\"" + ErrorMessageEnum.INCORRECT_STATE_MESSAGE.getMessage() + "\"}";
        assertEquals(response.getContentAsString(), responseBody);
        assertEquals(response.getStatus(), HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void setStatusSuccess() throws Exception {
        StatusDTO statusDTO = ovenController.getStatus(StatusEnum.POWER.getValue());
        assertEquals(statusDTO.getState(), PowerStateEnum.OFF.getValue());

        String requestBody = "{\"status\": \"" + StatusEnum.POWER.getValue() + "\", \"state\": \"" + PowerStateEnum.ON.getValue()+ "\"}";

        mockMvc.perform(put("/ovenApi")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );

        statusDTO = ovenController.getStatus(StatusEnum.POWER.getValue());
        assertEquals(statusDTO.getState(), PowerStateEnum.ON.getValue());
    }
}