package mapper;

import dao.StatusDAO;
import dto.StatusDTO;

public class StatusMapper {

    public static StatusDTO daoToDto(StatusDAO statusDAO){
        return StatusDTO.builder()
                .status(statusDAO.getName())
                .state(statusDAO.getState())
                .build();
    }
}