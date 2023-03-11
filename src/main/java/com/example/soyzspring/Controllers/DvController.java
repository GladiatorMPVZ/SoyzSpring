package com.example.soyzspring.Controllers;


import com.example.soyzspring.Converters.DVConverter;
import com.example.soyzspring.Dto.DvDto;
import com.example.soyzspring.Exceptions.AppError;
import com.example.soyzspring.Service.DevicesVaporizersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dv")
public class DvController {

    private final DevicesVaporizersService dvService;
    private final DVConverter dvConverter;

    @PostMapping
    public ResponseEntity<?> addParallel(@RequestBody DvDto dvDto) {
        if (dvDto.getDeviceId() < 0 || dvDto.getVaporizerId() < 0) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "id устройства или испарителя не могут быть отрицательными"), HttpStatus.BAD_REQUEST);
        }
        if (dvService.isExists(dvDto.getDeviceId(), dvDto.getVaporizerId())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Связь уже существует"), HttpStatus.BAD_REQUEST);
        }
        dvService.addNewParallel(dvDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public List<DvDto> getAll() {
        return dvService.getAll().stream().map(dvConverter::entityToDto).collect(Collectors.toList());
    }

    @DeleteMapping
    public void deleteDV(@RequestParam Long deviceId, @RequestParam Long vaporizerId) {
        dvService.deleteParallel(deviceId, vaporizerId);
    }
}
