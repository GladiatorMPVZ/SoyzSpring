package com.example.soyzspring.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoxDto {
    private int boxNumber;
    private Long vaporId;
    private Long userId;

    public BoxDto(int boxNumber, Long vaporId) {
        this.boxNumber = boxNumber;
        this.vaporId = vaporId;
    }
}
