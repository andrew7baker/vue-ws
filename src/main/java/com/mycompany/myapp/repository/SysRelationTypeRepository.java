package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.SysRelationType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysRelationType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysRelationTypeRepository extends JpaRepository<SysRelationType, Long> {

}
