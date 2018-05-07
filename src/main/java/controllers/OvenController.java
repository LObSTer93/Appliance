package controllers;

import dto.StatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.StatusService;

@RestController
@RequestMapping(value = "/ovenApi")
public class OvenController {

    private StatusService statusService;

    @Autowired
    public OvenController(StatusService statusService) {
        this.statusService = statusService;
    }

    @RequestMapping(value = "/switchStatus", method = RequestMethod.GET)
    public StatusDTO getSwitchStatus(){
        return statusService.getSwitchStatus();
    }
}