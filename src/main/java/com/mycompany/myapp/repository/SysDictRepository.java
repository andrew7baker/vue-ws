package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.SysDict;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysDict entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysDictRepository extends JpaRepository<SysDict, Long> {

}
