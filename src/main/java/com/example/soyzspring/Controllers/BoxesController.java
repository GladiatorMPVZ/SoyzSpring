package com.example.soyzspring.Controllers;

import com.example.soyzspring.Repository.BoxesRepository;
import com.example.soyzspring.Service.BoxesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boxes")
public class BoxesController {

    private final BoxesRepository boxesRepository;

    @GetMapping
    public List<>
}
