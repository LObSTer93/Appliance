package service;

import dao.StatusDAO;
import dao.StatusRepository;
import dto.StatusDTO;
import exceptions.ErrorMessageEnum;
import status_enums.*;
import exceptions.ApiException;
import dto.StatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatusServiceImpl implements StatusService {

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
    public StatusDTO getStatus(String status) {
        if(!EnumUtils.isEnumContainsValue(StatusEnum.values(), status)){
            throw new ApiException(ErrorMessageEnum.INCORRECT_STATUS_MESSAGE.getMessage());
        }
        StatusDAO statusDAO = statusRepository.findByName(status);
        return StatusMapper.daoToDto(statusDAO);
    }

    @Override
    public void editStatus(String status, String state) {
        checkValues(status, state);
        statusRepository.edit(status, state);
    }

    private void checkValues(String status, String state){
        if(status == null){
            throw new ApiException(ErrorMessageEnum.NO_STATUS_MESSAGE.getMessage());
        }
        if(state == null){
            throw new ApiException(ErrorMessageEnum.NO_STATE_MESSAGE.getMessage());
        }
        if(!EnumUtils.isEnumContainsValue(StatusEnum.values(), status)){
            throw new ApiException(ErrorMessageEnum.INCORRECT_STATUS_MESSAGE.getMessage());
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
            throw new ApiException(ErrorMessageEnum.INCORRECT_STATE_MESSAGE.getMessage());
        }
    }
}
