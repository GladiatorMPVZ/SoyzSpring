package com.example.soyzspring.Controllers;


import com.example.soyzspring.Dto.DvDto;
import com.example.soyzspring.Exceptions.AppError;
import com.example.soyzspring.Service.DevicesVaporizersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dv")
public class DvController {

    private final DevicesVaporizersService dvService;

    @PostMapping
    public ResponseEntity<?> addParallel(@RequestBody DvDto dvDto) {
        if (dvDto.getDeviceId() < 0 || dvDto.getVaporizerId() < 0) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "id устройства или испарителя не могут быть отрицательными"), HttpStatus.BAD_REQUEST);
        }
        //TODO Сделать проверку на существование связи
//        if (dvService.isExists(dvDto.getDeviceId(), dvDto.getVaporizerId())) {
//            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Связь уже существует"), HttpStatus.BAD_REQUEST);
//        }
        dvService.addNewParallel(dvDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
