package com.gran.imperva.goodservice.services.models.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class GoodDTO {

    private Integer id;
    private Integer amount;
    private String description;

}
