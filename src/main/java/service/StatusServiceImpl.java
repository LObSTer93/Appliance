package service;

import dao.StatusDAO;
import dao.StatusRepository;
import dto.StatusDTO;
import enums.*;
import exceptions.ApiException;
import mapper.StatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatusServiceImpl implements StatusService {

    static final String NO_STATUS_MESSAGE = "No status information is presented";
    static final String NO_STATE_MESSAGE = "No state information is presented";
    static final String INCORRECT_STATUS_MESSAGE = "There is no such status";
    public static final String INCORRECT_STATE_MESSAGE = "There is no such state";

    private StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository){
        this.statusRepository = statusRepository;
    }

    @Override
    public List<StatusDTO> getStatuses() {
        List<StatusDAO> statusDAOList = statusRepository.findAll();
        return statusDAOList.stream()
                .map(StatusMapper::daoToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void editStatus(String status, String state) {
        checkValues(status, state);
        statusRepository.edit(status, state);
    }

    private void checkValues(String status, String state){
        if(status == null){
            throw new ApiException(NO_STATUS_MESSAGE);
        }
        if(state == null){
            throw new ApiException(NO_STATE_MESSAGE);
        }
        if(!EnumUtils.isEnumContainsValue(StatusEnum.values(), status)){
            throw new ApiException(INCORRECT_STATUS_MESSAGE);
        }
        boolean isState = true;
        switch(status){
            case "Power":{
                if(!EnumUtils.isEnumContainsValue(PowerStateEnum.values(), state)){
                    isState = false;
                }
                break;
            }
            case "Door":{
                if(!EnumUtils.isEnumContainsValue(DoorStateEnum.values(), state)){
                    isState = false;
                }
                break;
            }
            case "Program":{
                if(!EnumUtils.isEnumContainsValue(ProgramStateEnum.values(), state)){
                    isState = false;
                }
                break;
            }
        }
        if(!isState){
            throw new ApiException(INCORRECT_STATE_MESSAGE);
        }
    }
}
