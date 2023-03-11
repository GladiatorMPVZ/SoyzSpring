package com.example.soyzspring.Service;

import com.example.soyzspring.Dto.BoxDto;
import com.example.soyzspring.Exceptions.AppError;
import com.example.soyzspring.Repository.UserRepository;
import com.example.soyzspring.Repository.VaporizersRepository;
import com.example.soyzspring.ResultForms.SearchBoxNumberResult;
import com.example.soyzspring.Repository.BoxesRepository;
import com.example.soyzspring.entity.Boxes;
import com.example.soyzspring.entity.User;
import com.example.soyzspring.entity.Vaporizers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BoxesService {

    private final BoxesRepository boxesRepository;
    private final UserRepository userRepository;

    public List<Boxes> findAll() {
        return boxesRepository.findAll();
    }

    public List<SearchBoxNumberResult> getBoxNumber(String title, Long userId) {
        return boxesRepository.findbyDeviceTitleAndShopId(title, userId);
    }

    public void saveNewBox(BoxDto boxDto, Principal principal) {
        Boxes boxes = new Boxes();
        User user = new User();
        Vaporizers vaporizers = new Vaporizers();
        user.setId(userRepository.findByUsername(principal.getName()).get().getId());
        vaporizers.setId(boxDto.getVaporId());
        boxes.setUserId(user);
        boxes.setVaporizersId(vaporizers);
        boxes.setNumber(boxDto.getBoxNumber());
        boxesRepository.save(boxes);
    }


}
