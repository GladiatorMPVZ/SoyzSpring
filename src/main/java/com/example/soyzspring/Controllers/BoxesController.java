package com.example.soyzspring.Controllers;

import com.example.soyzspring.Dto.Result;
import com.example.soyzspring.Dto.InputForResult;
import com.example.soyzspring.Service.BoxesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boxes")
public class BoxesController {
    private final BoxesService boxesService;

    @GetMapping
    public ResponseEntity<List<Result>> getNumber(@RequestBody InputForResult testInput) {
        List<Result> resultList = boxesService.getBoxNumber(testInput.getUserId(), testInput.getTitle());
        return ResponseEntity.ok(resultList);
    }
}
