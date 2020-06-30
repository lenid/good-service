package com.gran.imperva.goodservice.dao.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer amount;
    @Column(nullable = false, unique=true)
    private String description;

}
