package com.example.soyzspring.Repository;

import com.example.soyzspring.entity.Devices;
import com.example.soyzspring.entity.DevicesVaporizers;
import com.example.soyzspring.entity.Vaporizers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DevicesVaporizersRepository extends JpaRepository<DevicesVaporizers, Long> {

    Optional<DevicesVaporizers> findByDevicesIdAndVaporizersIdForDV(Devices devicesId, Vaporizers vaporizersIdForDV);

    Optional<DevicesVaporizers> deleteByDevicesIdAndVaporizersIdForDV(Devices devicesId, Vaporizers vaporizersIdForDV);

}
