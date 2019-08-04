package com.example.smartcard.repository;

import com.example.smartcard.domain.Complaint;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface ComplaintRepository extends CrudRepository<Complaint, Long> {
    
    List<Complaint> findAllByManipulatedIsFalse();
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE Complaint SET manipulated =1, notice =:notice WHERE id =:id")
    int updateAllLeavesSetApprovalForId(@Param("id") Long id, @Param("notice") String notice);
}
