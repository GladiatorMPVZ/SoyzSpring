package com.example.soyzspring.Service;

import com.example.soyzspring.ResultForms.SearchBoxNumberResult;
import com.example.soyzspring.Repository.BoxesRepository;
import com.example.soyzspring.entity.Boxes;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoxesService {

    private final BoxesRepository boxesRepository;
    private final JdbcTemplate jdbcTemplate;

    public List<Boxes> findAll() {
        return boxesRepository.findAll();
    }

    public List<SearchBoxNumberResult> getBoxNumber(String title, Long userId) {
        return boxesRepository.findbyDeviceTitleAndShopId(title, userId);
    }


}
