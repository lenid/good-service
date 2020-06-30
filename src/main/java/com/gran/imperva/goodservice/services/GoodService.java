package com.gran.imperva.goodservice.services;

import com.gran.imperva.goodservice.exceptions.InvalidException;
import com.gran.imperva.goodservice.services.models.dto.GoodDTO;

import java.util.List;

public interface GoodService {
    List<GoodDTO> getAll();
    GoodDTO create(GoodDTO goodDTO) throws InvalidException;
    GoodDTO update(GoodDTO goodDTO) throws InvalidException;
    void delete(GoodDTO goodDTO);
}
