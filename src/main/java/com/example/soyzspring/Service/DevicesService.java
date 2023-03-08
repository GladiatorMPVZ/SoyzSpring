package com.example.soyzspring.Service;


import com.example.soyzspring.Dto.DeviceDto;
import com.example.soyzspring.ResultForms.SearchDevVapResult;
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
    private final JdbcTemplate jdbcTemplate;

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

    public List<SearchDevVapResult> searchDeviceResults(String vaporizerTitle) {
        return jdbcTemplate.query(
                "SELECT devices.id, devices.device_title FROM devices " +
                        "INNER JOIN devices_vaporizers " +
                        "ON devices.id = devices_vaporizers.device_id " +
                        "INNER JOIN vaporizers " +
                        "ON vaporizers.id = devices_vaporizers.vaporizer_id " +
                        "WHERE vaporizers.title = '" + vaporizerTitle + "'",
                (rs, rowNum) -> new SearchDevVapResult(rs.getLong("id"), rs.getString("device_title"))
        );
    }

}
