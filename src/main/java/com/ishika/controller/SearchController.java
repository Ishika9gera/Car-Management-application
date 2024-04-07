package com.ishika.controller;

import com.ishika.controller.mapper.CarMapper;
import com.ishika.controller.mapper.DriverMapper;
import com.ishika.dataaccessobject.CarRepository;
import com.ishika.dataaccessobject.DriverRepository;
import com.ishika.datatransferobject.CarDTO;
import com.ishika.datatransferobject.DriverDTO;
import com.ishika.domainvalue.OnlineStatus;
import com.ishika.exception.ConstraintsViolationException;
import com.ishika.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/search")
public class SearchController {
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CarRepository carRepository;


    @GetMapping("/drivers/onlineStatus")
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
            throws ConstraintsViolationException, EntityNotFoundException {
        return DriverMapper.makeDriverDTOList(driverRepository
                .findByOnlineStatus(onlineStatus));
    }


    @GetMapping("/drivers/username")
    public List<DriverDTO> findDriversByUsername(
            @RequestParam String username)
            throws ConstraintsViolationException, EntityNotFoundException {
        return DriverMapper.makeDriverDTOList(driverRepository
                .findAllByUsernameIsContaining(username));
    }


    @GetMapping("/drivers/selectedcar")
    public List<DriverDTO> findDriversBySelectedCar()
            throws ConstraintsViolationException, EntityNotFoundException {
        return DriverMapper.makeDriverDTOList(driverRepository
                .findAllBySelectedCarNotNull());
    }


    @GetMapping("/drivers/attributes")
    public List<DriverDTO> findDriversByAttributes(
            @RequestParam(required = false, defaultValue = "") OnlineStatus onlineStatus,
            @RequestParam(required = false, defaultValue = "") String username)
            throws ConstraintsViolationException, EntityNotFoundException {
        return DriverMapper.makeDriverDTOList(driverRepository
                .findAllByOnlineStatusEqualsAndUsernameIsContaining(onlineStatus, username));
    }


    @GetMapping("/cars/attributes")
    public List<CarDTO> findCarsByAttributes(
            @RequestParam(required = false, defaultValue = "") String licensePlate,
            @RequestParam(required = false, defaultValue = "0") Integer seatCount)
            throws ConstraintsViolationException, EntityNotFoundException {
        return CarMapper.makeCarDTOList(carRepository
                .findAllByLicensePlateContainsOrSeatCountIsGreaterThanEqual(licensePlate, seatCount));
    }


    @GetMapping("/cars/manufacturer")
    public List<CarDTO> findCarsByManufacturer(
            @RequestParam(required = false, defaultValue = "") String manufacturer)
            throws ConstraintsViolationException, EntityNotFoundException {
        return CarMapper.makeCarDTOList(carRepository.findAllByManufacturerIsStartingWith(manufacturer));
    }


}
