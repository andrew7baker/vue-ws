package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.SysOperationLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysOperationLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysOperationLogRepository extends JpaRepository<SysOperationLog, Long> {

}
