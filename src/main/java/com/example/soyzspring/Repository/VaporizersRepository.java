package com.example.soyzspring.Repository;

import com.example.soyzspring.entity.Vaporizers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaporizersRepository extends JpaRepository<Vaporizers, Long> {

}
