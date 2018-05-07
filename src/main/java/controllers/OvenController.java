package controllers;

import dto.StatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.StatusService;

import java.util.List;

@RestController
@RequestMapping(value = "/ovenApi")
public class OvenController {

    private StatusService statusService;

    @Autowired
    public OvenController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public List<StatusDTO> getStatuses(){
        return statusService.getStatuses();
    }
}