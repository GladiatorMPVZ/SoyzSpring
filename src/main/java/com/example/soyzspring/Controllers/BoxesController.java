package com.example.soyzspring.Controllers;

import com.example.soyzspring.Dto.Result;
import com.example.soyzspring.Dto.InputForResult;
import com.example.soyzspring.Service.BoxesService;
import com.example.soyzspring.Service.UserService;
import com.example.soyzspring.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boxes")
public class BoxesController {
    private final BoxesService boxesService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<Result>> getNumber(@RequestParam String title, Principal principal) {
        Long res = userService.findByUsername(principal.getName()).get().getId();
        List<Result> resultList = boxesService.getBoxNumber(res.intValue(), title);
        return ResponseEntity.ok(resultList);
    }
}
