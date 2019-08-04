package com.example.smartcard.repository;

import com.example.smartcard.domain.Request;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RequestRepository extends CrudRepository<Request, Long>{
    
    List<Request> findAllByManipulated(boolean manipulated);
    
    List<Request> findAllByApprovalAndManipulated(boolean approval, boolean manipulated);
    
    List<Request> findAllByNationalId (Long nationalId);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE Request SET approval =:status, manipulated =1 WHERE id =:id")
    int updateAllRequestSetApprovalForId(@Param("id") Long id, @Param("status") boolean status);
}
