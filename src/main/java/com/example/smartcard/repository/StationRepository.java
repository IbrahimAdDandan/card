package com.example.smartcard.repository;

import com.example.smartcard.domain.Station;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface StationRepository extends CrudRepository<Station, Long>{
    
    // Like address!
//    List<Station> findAllOrderByQueueCountAscAmmountDesc();
    @Query(value = "SELECT s FROM Station s ORDER BY s.queueCount ASC, s.ammount DESC")
    List<Station> orderedStations();
    
    @Query(value = "SELECT s FROM Station s WHERE address LIKE :address")
    List<Station> orderedStationsByAddress(@Param("address") String address);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE Station SET queueCount = queueCount + 1 WHERE id =:id")
    int addToQueueCount(@Param("id") Long id);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE Station SET ammount = ammount - :fill, queueCount = queueCount - 1 WHERE id =:id")
    int fillOperation(@Param("id") Long id, @Param("fill") double fill);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE Station SET ammount = ammount + :fill WHERE id =:id")
    int refillOperation(@Param("id") Long id, @Param("fill") double fill);
}
