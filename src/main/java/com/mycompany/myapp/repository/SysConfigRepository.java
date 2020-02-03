package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.SysConfig;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysConfigRepository extends JpaRepository<SysConfig, Long> {

}
