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
            "INNER JOIN Devices d ON d.id = dv.devicesId.id " +
            "WHERE d.title = :deviceTitle")
    List<SearchDevVapResult> findByDeviceTitle(@Param("deviceTitle") String deviceTitle);

    Optional<Vaporizers> findByTitle(String title);
}
