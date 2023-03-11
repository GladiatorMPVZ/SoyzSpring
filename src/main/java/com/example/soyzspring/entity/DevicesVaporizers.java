package com.example.soyzspring.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "devices_vaporizers")
public class DevicesVaporizers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Devices devicesId;

    @ManyToOne
    @JoinColumn(name = "vaporizer_id")
    private Vaporizers vaporizersIdForDV;

}
