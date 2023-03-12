package com.example.soyzspring.Service;


import com.example.soyzspring.Dto.DeviceDto;
import com.example.soyzspring.Dto.VaporizerDto;
import com.example.soyzspring.ResultForms.SearchDevVapResult;
import com.example.soyzspring.Repository.DevicesRepository;
import com.example.soyzspring.entity.Devices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public List<SearchDevVapResult> searchDeviceResults(Long vaporizerId) {
        return devicesRepository.findByVaporizersIdForDv(vaporizerId);
    }

    public boolean isExists(String deviceTitle) {
        return devicesRepository.findByTitle(deviceTitle).isPresent();
    }

    public ResponseEntity<?> updateName(VaporizerDto vaporizerDto) {
        Optional<Devices> myModel = devicesRepository.findById(vaporizerDto.getId());
        if (!myModel.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Devices devices = myModel.get();
        devices.setTitle(vaporizerDto.getTitle());
        devicesRepository.save(devices);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

}
