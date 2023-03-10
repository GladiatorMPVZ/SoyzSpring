package com.example.soyzspring.Controllers;

import com.example.soyzspring.ResultForms.SearchBoxNumberResult;
import com.example.soyzspring.Service.BoxesService;
import com.example.soyzspring.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boxes")
public class BoxesController {
    private final BoxesService boxesService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<SearchBoxNumberResult>> getNumber(@RequestParam String deviceTitle, Principal principal) {
        Long userId = userService.findByUsername(principal.getName()).get().getId();
        List<SearchBoxNumberResult> resultList = boxesService.getBoxNumber(deviceTitle, userId);
        return ResponseEntity.ok(resultList);
    }
}
