package controllers;

import dto.StatusDTO;
import enums.PowerStateEnum;
import enums.StatusEnum;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import service.StatusService;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class OvenControllerTest {

    private final StatusService statusService = Mockito.mock(StatusService.class);
    private final OvenController ovenController = new OvenController(statusService);
    private final MockMvc mockMvc = standaloneSetup(ovenController).build();

    @Test
    public void getStatuses() throws Exception {
        when(statusService.getStatuses()).thenReturn(Collections.singletonList(getStatusDTO()));

        MvcResult mvcResult = mockMvc.perform(get("/")).andReturn();
        String responseMessage = "[" + getResponseMessage() + "]";

        assertEquals(mvcResult.getResponse().getContentAsString(), responseMessage);
        verify(statusService, times(1)).getStatuses();
    }

    @Test
    public void getStatus() throws Exception {
        when(statusService.getStatus(StatusEnum.POWER.getValue())).thenReturn(getStatusDTO());

        MvcResult mvcResult = mockMvc.perform(get("/" + StatusEnum.POWER.getValue())).andReturn();
        String responseMessage = getResponseMessage();

        assertEquals(mvcResult.getResponse().getContentAsString(), responseMessage);
        verify(statusService, times(1)).getStatus(StatusEnum.POWER.getValue());
    }

    @Test
    public void setStatus() throws Exception {
        String requestBody = "{\"status\": \"" + StatusEnum.POWER.getValue() + "\", \"state\": \"" + PowerStateEnum.ON.getValue()+ "\"}";

        mockMvc.perform(put("/ovenApi")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );

        verify(statusService, times(1)).editStatus(StatusEnum.POWER.getValue(), PowerStateEnum.ON.getValue());
    }

    private String getResponseMessage(){
        return "{\"status\":\"" + StatusEnum.POWER.getValue() + "\",\"state\":\"" + PowerStateEnum.OFF.getValue() + "\"}";
    }

    private static StatusDTO getStatusDTO(){
        return StatusDTO.builder()
                .status(StatusEnum.POWER.getValue())
                .state(PowerStateEnum.OFF.getValue())
                .build();
    }
}