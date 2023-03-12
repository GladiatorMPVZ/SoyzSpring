package com.example.soyzspring.ResultForms;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SearchBoxNumberResult {

    private Long deviceId;
    private Integer boxNumber;
    private String title;

    public SearchBoxNumberResult(Long deviceId, Integer boxNumber, String title) {
        this.deviceId = deviceId;
        this.boxNumber = boxNumber;
        this.title = title;
    }

    public SearchBoxNumberResult(Integer boxNumber, String title) {
        this.boxNumber = boxNumber;
        this.title = title;
    }
}
