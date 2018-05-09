package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface StatusRepository extends JpaRepository<StatusDAO, Long> {

    @Modifying
    @Query("update StatusDAO i set i.state=:state where i.name=:name")
    void edit(@Param("name") String name, @Param("state") String state);

    StatusDAO findByName(String name);
}