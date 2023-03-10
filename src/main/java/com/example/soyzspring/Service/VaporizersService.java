package com.example.soyzspring.Service;


import com.example.soyzspring.Dto.VaporizerDto;
import com.example.soyzspring.Repository.VaporizersRepository;
import com.example.soyzspring.ResultForms.SearchDevVapResult;
import com.example.soyzspring.entity.Vaporizers;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaporizersService {

    private final VaporizersRepository vaporizersRepository;

    public List<Vaporizers> findAll() {
        return vaporizersRepository.findAll();
    }

    public void deleteById(Long id) {
        vaporizersRepository.deleteById(id);
    }

    public Optional<Vaporizers> findById(Long id) {
        return vaporizersRepository.findById(id);
    }

    public void createNewVaporizer(VaporizerDto vaporizerDto) {
        Vaporizers vaporizer = new Vaporizers();
        vaporizer.setTitle(vaporizerDto.getTitle());
        vaporizersRepository.save(vaporizer);
    }

    public List<SearchDevVapResult> searchDeviceResults(String deviceTitle) {
        return vaporizersRepository.findByDeviceTitle(deviceTitle);
    }
}
