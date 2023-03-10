package com.example.soyzspring.Controllers;


import com.example.soyzspring.Converters.VaporizerConverter;
import com.example.soyzspring.Exceptions.AppError;
import com.example.soyzspring.ResultForms.SearchDevVapResult;
import com.example.soyzspring.Dto.VaporizerDto;
import com.example.soyzspring.Exceptions.ResourceNotFoundException;
import com.example.soyzspring.Service.VaporizersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vaporizers")
public class VaporizersController {
    private final VaporizersService vaporizersService;
    private final VaporizerConverter vaporizerConverter;

    @GetMapping("/filtered")
    public ResponseEntity<List<SearchDevVapResult>> getSuitableVaporizers(@RequestParam String deviceTitle) {
        List<SearchDevVapResult> resultList = vaporizersService.searchDeviceResults(deviceTitle);
        return ResponseEntity.ok(resultList);
    }

    @GetMapping
    public List<VaporizerDto> getAllVaporizers() {
        return vaporizersService.findAll().stream().map(vaporizerConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public VaporizerDto getVaporizerById(@PathVariable Long id) {
        return vaporizerConverter.entityToDto(vaporizersService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Испарителя с id: " + id + "не найден")));
    }

    @PostMapping
    public ResponseEntity<?> createNewVaporizer(@RequestBody VaporizerDto vaporizerDto) {
        if (vaporizerDto.getTitle().length() == 0) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Название испарителя не может быть пустым полем"), HttpStatus.BAD_REQUEST);
        }
        if (vaporizersService.isExists(vaporizerDto.getTitle())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Такой испаритель уже существует"), HttpStatus.BAD_REQUEST);
        }
        vaporizersService.createNewVaporizer(vaporizerDto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteVaporizerById(@PathVariable Long id) {
        vaporizersService.deleteById(id);
    }


}
