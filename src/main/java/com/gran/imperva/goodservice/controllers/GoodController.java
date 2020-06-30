package com.gran.imperva.goodservice.controllers;

import com.gran.imperva.goodservice.exceptions.InvalidException;
import com.gran.imperva.goodservice.services.GoodService;
import com.gran.imperva.goodservice.services.models.dto.GoodDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @GetMapping
    public List<GoodDTO> getGoods() {
        log.info("Get all goods");

        return goodService.getAll();
    }

    @PostMapping
    public Object addGood(GoodDTO goodDTO) {
        log.info("Add good {}", goodDTO);

        try {
            return goodService.create(goodDTO);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        } catch (InvalidException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Adding good {} calls the error {}", goodDTO, e.toString());
            throw e;
        }
    }

    @PutMapping(path = "/{id}")
    public Object updateGood(@PathVariable Integer id, GoodDTO goodDTO) {
        log.info("Update good {}", goodDTO);

        if (!id.equals(goodDTO.getId())) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        try {
            return goodService.update(goodDTO);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        } catch (InvalidException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Updating good {} calls the error {}", goodDTO, e.toString());
            throw e;
        }
    }

    @DeleteMapping(path = "/{id}")
    public Object deleteGood(@PathVariable Integer id, GoodDTO goodDTO) {
        log.info("Delete good {}", goodDTO);

        if (!id.equals(goodDTO.getId())) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        goodService.delete(goodDTO);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
