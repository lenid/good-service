package com.gran.imperva.goodservice.converters;

import com.gran.imperva.goodservice.dao.models.Good;
import com.gran.imperva.goodservice.services.models.dto.GoodDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GoodDTOToGoodConverter implements Converter<GoodDTO, Good> {

    @Override
    public Good convert(GoodDTO goodDTO) {
        Good good = new Good();

        good.setId(goodDTO.getId());
        good.setAmount(goodDTO.getAmount());
        good.setDescription(goodDTO.getDescription());

        return good;
    }
}
