package com.example.soyzspring.Service;


import com.example.soyzspring.Dto.DeviceDto;
import com.example.soyzspring.Dto.VaporizerDto;
import com.example.soyzspring.Repository.VaporizersRepository;
import com.example.soyzspring.ResultForms.SearchDevVapResult;
import com.example.soyzspring.entity.Devices;
import com.example.soyzspring.entity.Vaporizers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaporizersService {

    private final VaporizersRepository vaporizersRepository;

    public List<Vaporizers> findAll() {
        return vaporizersRepository.findAll();
    }

    public void deleteById(Long id) {
        vaporizersRepository.deleteById(id);
    }

    public Optional<Vaporizers> findById(Long id) {
        return vaporizersRepository.findById(id);
    }

    public Vaporizers createNewVaporizer(VaporizerDto vaporizerDto) {
        Vaporizers vaporizer = new Vaporizers();
        vaporizer.setTitle(vaporizerDto.getTitle());
        return vaporizersRepository.save(vaporizer);
    }

    public List<SearchDevVapResult> searchDeviceResults(Long deviceId) {
        return vaporizersRepository.findByDevicesId(deviceId);
    }

    public boolean isExists(String deviceTitle) {
        return vaporizersRepository.findByTitle(deviceTitle).isPresent();
    }

    public ResponseEntity<?> updateName(DeviceDto deviceDto) {
        Optional<Vaporizers> myModel = vaporizersRepository.findById(deviceDto.getId());
        if (!myModel.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Vaporizers vaporizers = myModel.get();
        vaporizers.setTitle(deviceDto.getTitle());
        vaporizersRepository.save(vaporizers);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
