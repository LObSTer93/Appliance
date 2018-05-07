package dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusDAO, Long> {

    StatusDAO getByName(String name);
}