package com.example.soyzspring.Repository;

import com.example.soyzspring.ResultForms.SearchBoxNumberResult;
import com.example.soyzspring.entity.Boxes;
import com.example.soyzspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoxesRepository extends JpaRepository<Boxes, Long> {
    @Query("SELECT new com.example.soyzspring.ResultForms.SearchBoxNumberResult(b.number,v.id, v.title)" +
            "FROM Boxes b " +
            "INNER JOIN Vaporizers v ON b.vaporizersId.id = v.id " +
            "INNER JOIN DevicesVaporizers dv ON v.id = dv.vaporizersIdForDV.id " +
            "WHERE dv.devicesId.id = :deviceId AND b.userId.id = :userId")
    List<SearchBoxNumberResult> findbyDevicesIdAndShopId(@Param("deviceId") Long deviceId, @Param("userId") Long userId);

    List<Boxes> findByUserId(User userId);
}
