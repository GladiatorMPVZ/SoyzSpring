package com.example.soyzspring.Controllers;


import com.example.soyzspring.Converters.VaporizerConverter;
import com.example.soyzspring.Dto.VaporizerDto;
import com.example.soyzspring.Repository.VaporizersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vaporizers")
public class VaporizersController {
    private final VaporizersRepository vaporizersRepository;
    private final VaporizerConverter vaporizerConverter;

    @GetMapping
    public List<VaporizerDto> getAllVaporizers() {
        return vaporizersRepository.findAll().stream().map(vaporizerConverter::entityToDto).collect(Collectors.toList());
    }
}
