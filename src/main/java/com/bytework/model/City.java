package com.bytework.model;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
