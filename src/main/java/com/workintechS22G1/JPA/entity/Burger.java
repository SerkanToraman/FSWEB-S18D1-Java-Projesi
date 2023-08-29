package com.workintechS22G1.JPA.entity;

import com.workintechS22G1.JPA.entity.enums.BreadType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name ="burger", schema ="s22g1jpa")
public class Burger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
//    @Positive
    private int id;

    @Column(name="name")
    @NotNull
    @NotBlank
    private String name;

    @Column(name="price")
    @DecimalMin("20")
    private double price;

    @Column(name="is_vegan")
    private boolean isVegan;

    @Enumerated(EnumType.STRING)
    private BreadType breadType;

    @Column(name="contents")
    private List<String> contents;

}
