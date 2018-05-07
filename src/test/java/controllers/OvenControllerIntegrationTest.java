package controllers;

import config.DBConfig;
import config.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DBConfig.class, IntegrationConfig.class})
public class OvenControllerIntegrationTest {

    @Autowired
    private OvenController ovenController;

    @Test
    public void getSwitchStatus() throws Exception {
        MockMvc mockMvc = standaloneSetup(ovenController).build();
        MvcResult result = mockMvc.perform(get("/ovenApi/switchStatus")).andReturn();
        String responseMessage = "{\"status\":\"switch\",\"state\":\"off\"}";
        assertEquals(result.getResponse().getContentAsString(), responseMessage);
    }
}