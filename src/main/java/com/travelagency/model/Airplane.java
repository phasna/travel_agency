package com.travelagency.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "airplane")
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
} 