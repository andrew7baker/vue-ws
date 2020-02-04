package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.SysFileInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysFileInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysFileInfoRepository extends JpaRepository<SysFileInfo, Long> {

}
