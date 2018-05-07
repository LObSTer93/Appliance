package service;

import dao.StatusDAO;
import dao.StatusRepository;
import dto.StatusDTO;
import mapper.StatusMapper;
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
}
