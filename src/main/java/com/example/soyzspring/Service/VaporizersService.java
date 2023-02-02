package com.example.soyzspring.Service;


import com.example.soyzspring.Repository.VaporizersRepository;
import com.example.soyzspring.entity.Vaporizers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
}
