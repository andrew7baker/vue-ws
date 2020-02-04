package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.SysRelation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysRelation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysRelationRepository extends JpaRepository<SysRelation, Long> {

}
