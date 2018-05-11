package dto;

import dao.StatusDAO;

public class StatusMapper {

    public static StatusDTO daoToDto(StatusDAO statusDAO){
        return StatusDTO.builder()
                .status(statusDAO.getName())
                .state(statusDAO.getState())
                .build();
    }
}