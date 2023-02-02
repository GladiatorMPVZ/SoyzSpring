package com.example.soyzspring.Service;

import com.example.soyzspring.Repository.BoxesRepository;
import com.example.soyzspring.entity.Boxes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoxesService {

    private final BoxesRepository boxesRepository;

    public List<Boxes> findAll() {
        return boxesRepository.findAll();
    }

//TODO подумать где и как реализовывать поиск яйчейки
    public void searchBoxNumber(Long shopId, String deviceTitle) {

    }


}
