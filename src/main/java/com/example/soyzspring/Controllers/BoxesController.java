package com.example.soyzspring.Controllers;

import com.example.soyzspring.Service.BoxesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boxes")
public class BoxesController {

    private final BoxesService boxesService;
}
