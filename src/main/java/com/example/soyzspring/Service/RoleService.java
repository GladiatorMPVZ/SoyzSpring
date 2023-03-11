package com.example.soyzspring.Service;

import com.example.soyzspring.Repository.RoleRepository;
import com.example.soyzspring.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRole(Long id) {
        return roleRepository.getById(id);
    }
}
