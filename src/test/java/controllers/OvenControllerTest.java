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
        StatusDTO statusDTO = StatusDTO.builder()
                .status(StatusEnum.POWER.getValue())
                .state(PowerStateEnum.OFF.getValue())
                .build();
        when(statusService.getStatuses()).thenReturn(Collections.singletonList(statusDTO));

        MvcResult mvcResult = mockMvc.perform(get("/ovenApi")).andReturn();
        String responseMessage = "[{\"status\":\"" + StatusEnum.POWER.getValue() + "\",\"state\":\"" + PowerStateEnum.OFF.getValue() + "\"}]";

        assertEquals(mvcResult.getResponse().getContentAsString(), responseMessage);
        verify(statusService, times(1)).getStatuses();
    }

    @Test
    public void setStatus() throws Exception {
        String body = "{\"status\": \"Power\", \"state\": \"On\"}";

        MvcResult mvcResult = mockMvc.perform(put("/ovenApi")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        ).andReturn();
    }
}