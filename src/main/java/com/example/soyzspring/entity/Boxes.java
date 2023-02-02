package com.example.soyzspring.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "boxes")
public class Boxes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "box_number")
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "vapor_id")
    private Vaporizers vaporizersId;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private User userId;
}
