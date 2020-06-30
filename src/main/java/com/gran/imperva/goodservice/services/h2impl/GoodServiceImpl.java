package com.gran.imperva.goodservice.services.h2impl;

import com.gran.imperva.goodservice.dao.GoodDAO;
import com.gran.imperva.goodservice.dao.models.Good;
import com.gran.imperva.goodservice.exceptions.InvalidException;
import com.gran.imperva.goodservice.services.GoodService;
import com.gran.imperva.goodservice.services.models.dto.GoodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class GoodServiceImpl implements GoodService {

    private final int trayVolume;

    @Autowired
    private GoodDAO goodDAO;

    @Autowired
    @Qualifier("mvcConversionService")
    private ConversionService conversionService;

    public GoodServiceImpl(@Value("${tray.volume}") int trayVolume) {
        this.trayVolume = trayVolume;
    }

    @Override
    public List<GoodDTO> getAll() {

        return goodDAO.findAll().stream()
                .sorted(Comparator.comparing(Good::getDescription))
                .map(good -> conversionService.convert(good, GoodDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public GoodDTO getById(int id) {
        Good good = goodDAO.findById(id).orElse(null);

        if (good == null) {
            return null;
        }

        return conversionService.convert(good, GoodDTO.class);
    }

    @Override
    public GoodDTO create(GoodDTO goodDTO) throws InvalidException {
        validateGoodDTO(goodDTO);
        Good goodNew = conversionService.convert(goodDTO, Good.class);
        Good goodSaved = goodDAO.save(goodNew);

        return conversionService.convert(goodSaved, GoodDTO.class);
    }

    @Override
    public GoodDTO update(GoodDTO goodDTO) throws InvalidException {
        validateGoodDTO(goodDTO);
        goodDAO.findById(goodDTO.getId()).orElseThrow(NoSuchElementException::new);

        Good good = conversionService.convert(goodDTO, Good.class);
        Good goodSaved = goodDAO.save(good);

        return conversionService.convert(goodSaved, GoodDTO.class);
    }

    @Override
    public void delete(GoodDTO goodDTO) {
        Good good = conversionService.convert(goodDTO, Good.class);
        goodDAO.delete(good);
    }

    private void validateGoodDTO(GoodDTO goodDTO) throws InvalidException {
        if (goodDTO.getAmount() > trayVolume) {
            String errorMessage = String.format("Amount value must not exceed %d", trayVolume);
            throw new InvalidException(errorMessage);
        }
    }
}
