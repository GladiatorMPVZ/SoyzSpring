package com.example.soyzspring.Repository;

import com.example.soyzspring.entity.Boxes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxesRepository extends JpaRepository<Boxes, Long> {
//    @Query("SELECT new com.example.soyzspring.Dto.Result(b.number, v.title) " +
//            "FROM Boxes b " +
//            "INNER JOIN Vaporizers v ON b.vaporizersId = v.id " +
//            "INNER JOIN DevicesVaporizers dv ON v.id = dv.vaporizerId " +
//            "INNER JOIN Devices d ON dv.deviceId = d.id " +
//            "WHERE d.title = :deviceTitle AND b.userId = :shopId")
//    List<Result> findbyDeviceTitleAndShopId(@Param("deviceTitle") String deviceTitle, @Param("shopId") Long shopId);
}
