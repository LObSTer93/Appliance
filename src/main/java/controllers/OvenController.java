package controllers;

import dto.StatusDTO;
import dto.StatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.StatusService;

import java.util.List;

@RestController
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

    @GetMapping("/{status}")
    public StatusDTO getStatus(@PathVariable("status") String status){
        return statusService.getStatus(status);
    }

    @PutMapping
    public void setStatus(@RequestBody StatusRequest statusRequest){
        statusService.editStatus(statusRequest.getStatus(), statusRequest.getState());
    }
}