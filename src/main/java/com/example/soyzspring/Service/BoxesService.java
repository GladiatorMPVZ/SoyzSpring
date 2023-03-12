package com.example.soyzspring.Service;

import com.example.soyzspring.Converters.BoxConverter;
import com.example.soyzspring.Dto.BoxDto;
import com.example.soyzspring.Repository.BoxesRepository;
import com.example.soyzspring.Repository.UserRepository;
import com.example.soyzspring.ResultForms.SearchBoxNumberResult;
import com.example.soyzspring.entity.Boxes;
import com.example.soyzspring.entity.User;
import com.example.soyzspring.entity.Vaporizers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoxesService {

    private final BoxesRepository boxesRepository;
    private final UserRepository userRepository;
    private final BoxConverter boxConverter;

    public List<Boxes> findAll() {
        return boxesRepository.findAll();
    }

    public List<SearchBoxNumberResult> getBoxNumber(Long deviceId, Long userId) {
        return boxesRepository.findbyDevicesIdAndShopId(deviceId, userId);
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

    public List<BoxDto> getAllByUserId(Principal principal) {
        Long userId = userRepository.findByUsername(principal.getName()).get().getId();
        User user = new User();
        user.setId(userId);
        return boxesRepository.findByUserId(user).stream().map(boxConverter::entityToDto).collect(Collectors.toList());
    }


}
