package com.example.soyzspring.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "devices")
public class Devices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "device_title")
    private String title;

    @ManyToMany
    @JoinTable(name = "devices_vaporizers",
            joinColumns = @JoinColumn(name = "device_id"),
            inverseJoinColumns = @JoinColumn(name = "vaporizer_id"))
    private Collection<Vaporizers> vaporizers;

}
