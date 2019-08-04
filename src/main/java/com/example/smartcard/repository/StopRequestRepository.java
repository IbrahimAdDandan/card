package com.example.smartcard.repository;

import com.example.smartcard.domain.StopRequest;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StopRequestRepository extends CrudRepository<StopRequest, Long>{
    List<StopRequest> findAllByManipulated(boolean manipulated);
    
    List<StopRequest> findAllByApprovalAndManipulated(boolean approval, boolean manipulated);
    
    List<StopRequest> findAllByNationalId (Long nationalId);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE StopRequest SET approval =:status, manipulated =1 WHERE id =:id")
    int updateAllStopRequestSetApprovalForId(@Param("id") Long id, @Param("status") boolean status);
}
