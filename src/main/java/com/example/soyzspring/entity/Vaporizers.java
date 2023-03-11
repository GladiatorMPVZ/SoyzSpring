package com.example.soyzspring.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "vaporizers")
public class Vaporizers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "vaporizersIdForDV", cascade = CascadeType.ALL)
    private List<DevicesVaporizers> devicesVaporizersList;

    @OneToMany(mappedBy = "vaporizersId", cascade = CascadeType.ALL)
    private List<Boxes> boxesList;
}
