package com.example.smartcard.repository;

import com.example.smartcard.domain.SmartCard;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CardRepository extends CrudRepository<SmartCard, Long> {
    
    List<SmartCard> findAllByNationalId(Long nationalId);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE SmartCard SET restAmmount = restAmmount - :fill WHERE id =:id")
    int fillOperation(@Param("id") Long id, @Param("fill") double fill);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE SmartCard SET restAmmount = restAmmount + :fill WHERE id =:id")
    int refillOperation(@Param("id") Long id, @Param("fill") double fill);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE SmartCard SET status = 'VALID' WHERE id =:id")
    int activate(@Param("id") Long id);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE SmartCard SET status = 'INVALID' WHERE id =:id")
    int deactivate(@Param("id") Long id);
}
