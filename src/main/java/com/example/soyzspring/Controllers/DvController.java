package com.example.soyzspring.Controllers;


import com.example.soyzspring.Dto.DvDto;
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
        dvService.addNewParallel(dvDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
