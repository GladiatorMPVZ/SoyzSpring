package com.example.soyzspring.Service;

import com.example.soyzspring.Dto.Result;
import com.example.soyzspring.Repository.BoxesRepository;
import com.example.soyzspring.entity.Boxes;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoxesService {

    private final BoxesRepository boxesRepository;
    private final JdbcTemplate jdbcTemplate;

    public List<Boxes> findAll() {
        return boxesRepository.findAll();
    }

    public List<Result> getBoxNumber(Integer boxNumber, String title) {
        return jdbcTemplate.query(
                "SELECT boxes.box_number, vaporizers.title FROM boxes " +
                        "INNER JOIN vaporizers " +
                        "ON vaporizers.id = boxes.vapor_id " +
                        "INNER JOIN devices_vaporizers " +
                        "ON vaporizers.id = devices_vaporizers.vaporizer_id " +
                        "INNER JOIN devices " +
                        "ON devices.id = devices_vaporizers.device_id " +
                        "WHERE devices.device_title = '" + title + "' AND boxes.shop_id =" + boxNumber,
                (rs, rowNum) -> new Result(rs.getInt("box_number"), rs.getString("title"))
        );
    }


}
