package com.example.soyzspring.Converters;

import com.example.soyzspring.Dto.BoxDto;
import com.example.soyzspring.entity.Boxes;
import org.springframework.stereotype.Component;

@Component
public class BoxConverter {

    public BoxDto entityToDto(Boxes b) {
        BoxDto boxDto = new BoxDto();
        boxDto.setBoxNumber(b.getNumber());
        boxDto.setVaporId(b.getVaporizersId().getId());
        boxDto.setUserId(b.getUserId().getId());
        return boxDto;
    }
}
