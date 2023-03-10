package com.example.soyzspring.Repository;

import com.example.soyzspring.ResultForms.SearchBoxNumberResult;
import com.example.soyzspring.entity.Boxes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoxesRepository extends JpaRepository<Boxes, Long> {
    @Query("SELECT new com.example.soyzspring.ResultForms.SearchBoxNumberResult(b.number, v.title) " +
            "FROM Boxes b " +
            "INNER JOIN Vaporizers v ON b.vaporizersId.id = v.id " +
            "INNER JOIN DevicesVaporizers dv ON v.id = dv.vaporizersIdForDV.id " +
            "INNER JOIN Devices d ON dv.devicesId.id = d.id " +
            "WHERE d.title = :deviceTitle AND b.userId.id = :userId")
    List<SearchBoxNumberResult> findbyDeviceTitleAndShopId(@Param("deviceTitle") String deviceTitle, @Param("userId") Long userId);
}
