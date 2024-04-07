package com.ishika.controller;

import com.ishika.controller.mapper.CarMapper;
import com.ishika.datatransferobject.CarDTO;
import com.ishika.domainobject.CarDO;
import com.ishika.exception.ConstraintsViolationException;
import com.ishika.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * All operations with a car will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/cars")
public class CarController {

    @Autowired
    private CarService carService;


    @GetMapping("/available")
    public List<CarDTO> findCars(@RequestParam boolean available) {
        return CarMapper.makeCarDTOList(carService.find(available));
    }


    @GetMapping("/all")
    public List<CarDTO> findAllCars() {
        return CarMapper.makeCarDTOList(carService.findAll());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException {
        CarDO carDO = CarMapper.makeCarDO(carDTO);
        return CarMapper.makeCarDTO(carService.create(carDO));
    }

}
