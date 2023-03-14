package com.example.soyzspring.ResultForms;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SearchBoxNumberResult {

    private Integer boxNumber;
    private Long vaporId;
    private String title;

    public SearchBoxNumberResult(Integer boxNumber, Long vaporId, String title) {
        this.boxNumber = boxNumber;
        this.vaporId = vaporId;
        this.title = title;
    }

    public SearchBoxNumberResult(Integer boxNumber, String title) {
        this.boxNumber = boxNumber;
        this.title = title;
    }
}
