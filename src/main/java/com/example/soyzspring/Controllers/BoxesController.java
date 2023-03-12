package com.example.soyzspring.Controllers;

import com.example.soyzspring.Dto.BoxDto;
import com.example.soyzspring.Exceptions.AppError;
import com.example.soyzspring.ResultForms.SearchBoxNumberResult;
import com.example.soyzspring.Service.BoxesService;
import com.example.soyzspring.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<SearchBoxNumberResult>> getNumber(@RequestParam Long deviceId, Principal principal) {
        Long userId = userService.findByUsername(principal.getName()).get().getId();
        List<SearchBoxNumberResult> resultList = boxesService.getBoxNumber(deviceId, userId);
        return ResponseEntity.ok(resultList);
    }

    @PostMapping
    public ResponseEntity<?> saveNewBox(@RequestBody BoxDto boxDto, Principal principal) {
        if (boxDto.getVaporId() < 0 || boxDto.getBoxNumber() < 0) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "id испарителя или номер коробки не могут быть отрицательными"), HttpStatus.BAD_REQUEST);
        }
        boxesService.saveNewBox(boxDto, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<BoxDto> getAllBoxes(Principal principal) {
        return boxesService.getAllByUserId(principal);
    }
}
