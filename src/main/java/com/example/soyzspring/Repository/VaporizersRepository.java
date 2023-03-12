package com.example.soyzspring.Repository;

import com.example.soyzspring.ResultForms.SearchDevVapResult;
import com.example.soyzspring.entity.Devices;
import com.example.soyzspring.entity.Vaporizers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VaporizersRepository extends JpaRepository<Vaporizers, Long> {
    @Query("SELECT new com.example.soyzspring.ResultForms.SearchDevVapResult(v.id, v.title) " +
            "FROM Vaporizers v " +
            "INNER JOIN DevicesVaporizers dv ON v.id = dv.vaporizersIdForDV.id " +
            "WHERE dv.devicesId.id = :deviceId")
    List<SearchDevVapResult> findByDevicesId(@Param("deviceId") Long deviceId);

    Optional<Vaporizers> findByTitle(String title);
    Optional<Vaporizers> findById(Long vaporizerId);
}
