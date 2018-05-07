package controllers;

import dto.StatusDTO;
import enums.PowerStateEnum;
import enums.StatusEnum;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import service.StatusService;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class OvenControllerTest {

    @Test
    public void getStatuses() throws Exception {
        StatusService statusService = Mockito.mock(StatusService.class);
        StatusDTO statusDTO = StatusDTO.builder()
                .status(StatusEnum.POWER.getName())
                .state(PowerStateEnum.OFF.getState())
                .build();
        when(statusService.getStatuses()).thenReturn(Collections.singletonList(statusDTO));
        OvenController ovenController = new OvenController(statusService);
        MockMvc mockMvc = standaloneSetup(ovenController).build();

        MvcResult result = mockMvc.perform(get("/ovenApi")).andReturn();
        String responseMessage = "[{\"status\":\"" + StatusEnum.POWER.getName() + "\",\"state\":\"" + PowerStateEnum.OFF.getState() + "\"}]";

        assertEquals(result.getResponse().getContentAsString(), responseMessage);
        verify(statusService, times(1)).getStatuses();
    }
}