package com.bytework.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "serviceProvider")
public class ServiceProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String city;
}
