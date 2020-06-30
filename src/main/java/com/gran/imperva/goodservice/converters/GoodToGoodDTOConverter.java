package com.gran.imperva.goodservice.converters;

import com.gran.imperva.goodservice.dao.models.Good;
import com.gran.imperva.goodservice.services.models.dto.GoodDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GoodToGoodDTOConverter implements Converter<Good, GoodDTO> {

    @Override
    public GoodDTO convert(Good good) {
        GoodDTO goodDTO = new GoodDTO();

        goodDTO.setId(good.getId());
        goodDTO.setAmount(good.getAmount());
        goodDTO.setDescription(good.getDescription());

        return goodDTO;
    }
}
