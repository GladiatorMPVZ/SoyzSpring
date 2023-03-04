package com.example.soyzspring.Controllers;


import com.example.soyzspring.Converters.VaporizerConverter;
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
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewVaporizer(@RequestBody VaporizerDto vaporizerDto) {
        vaporizersService.createNewVaporizer(vaporizerDto);
    }

    @DeleteMapping("/{id}")
    public void deleteVaporizerById(@PathVariable Long id) {
        vaporizersService.deleteById(id);
    }


}
