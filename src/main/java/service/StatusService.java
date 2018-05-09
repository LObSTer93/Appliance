package service;

import dto.StatusDTO;

import java.util.List;

public interface StatusService {

    List<StatusDTO> getStatuses();

    void editStatus(String statusName, String newState);
}
