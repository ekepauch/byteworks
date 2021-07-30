package com.bytework.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "meal")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String price;
    private Long providerId;


}
