package com.example.soyzspring.Service;


import com.example.soyzspring.Dto.DeviceDto;
import com.example.soyzspring.Repository.DevicesRepository;
import com.example.soyzspring.entity.Devices;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DevicesService {

    private final DevicesRepository devicesRepository;

    public List<Devices> findAll() {
        return devicesRepository.findAll();
    }

    public void deleteById(Long id) {
        devicesRepository.deleteById(id);
    }

    public Optional<Devices> findById(Long id) {
        return devicesRepository.findById(id);
    }

    public void createNewDevice(DeviceDto deviceDto) {
        Devices device = new Devices();
        device.setTitle(deviceDto.getTitle());
        devicesRepository.save(device);
    }

}
