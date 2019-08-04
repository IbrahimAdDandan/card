package com.example.smartcard.repository;

import com.example.smartcard.domain.FillOperation;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface FillOperationRepository extends CrudRepository<FillOperation, Long>{
    
    public static final String Q = "SELECT s.name, s.address, AVG(f.ammount) avgAmmount, SUM(f.ammount) fillCount FROM station s inner join fill_operation f on s.id = f.station_id GROUP BY s.id";
    @Query(value = Q, nativeQuery = true)
    List<Object[]> statistics();
}
