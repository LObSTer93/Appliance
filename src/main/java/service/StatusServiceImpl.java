package service;

import dao.StatusDAO;
import dao.StatusRepository;
import dto.StatusDTO;
import mapper.StatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatusServiceImpl implements StatusService {

    private StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository){
        this.statusRepository = statusRepository;
    }

    @Override
    public StatusDTO getSwitchStatus() {
        StatusDAO statusDAO = statusRepository.getByName("switch");
        return StatusMapper.daoToDto(statusDAO);
    }
}
