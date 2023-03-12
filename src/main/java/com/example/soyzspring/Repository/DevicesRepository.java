package com.example.soyzspring.Repository;


import com.example.soyzspring.ResultForms.SearchDevVapResult;
import com.example.soyzspring.entity.Devices;
import com.example.soyzspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DevicesRepository extends JpaRepository<Devices, Long> {
    @Query("SELECT new com.example.soyzspring.ResultForms.SearchDevVapResult(d.id, d.title) " +
            "FROM Devices d " +
            "INNER JOIN DevicesVaporizers dv ON d.id = dv.devicesId.id " +
            "WHERE dv.vaporizersIdForDV.id = :vaporizerId")
    List<SearchDevVapResult> findByVaporizersIdForDv(@Param("vaporizerId") Long vaporizerId);

    Optional<Devices> findByTitle(String title);
    Optional<Devices> findById(Long deviceId);
}
