package service;

import dto.StatusDTO;

import java.util.List;

public interface StatusService {

    List<StatusDTO> getStatuses();

    StatusDTO getStatus(String status);

    void editStatus(String statusName, String newState);
}
