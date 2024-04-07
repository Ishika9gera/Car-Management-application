package com.ishika.controller.mapper;

import com.ishika.datatransferobject.CarDTO;
import com.ishika.domainobject.CarDO;
import com.ishika.domainvalue.EngineType;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CarMapper {
    public static CarDO makeCarDO(CarDTO carDTO) {
        String licensePlate = carDTO.getLicensePlate();
        Boolean available = carDTO.getAvailable() != null ? carDTO.getAvailable() : true;
        Integer seatCount = carDTO.getSeatCount() != null ? carDTO.getSeatCount() : 5;
        Boolean convertible = carDTO.getConvertible() != null ? carDTO.getConvertible() : false;
        Integer rating = carDTO.getRating() != null ? carDTO.getRating() : 3;
        EngineType engineType = carDTO.getEngineType() != null ? carDTO.getEngineType() : EngineType.GAS;
        String manufacturer = carDTO.getManufacturer();
        return new CarDO(licensePlate, available, seatCount, convertible, rating, engineType, manufacturer);
    }


    public static CarDTO makeCarDTO(CarDO carDO) {
        CarDTO.CarDTOBuilder carDTOBuilder = CarDTO.newBuilder()
                .setId(carDO.getId())
                .setAvailable(carDO.getAvailable())
                .setLicensePlate(carDO.getLicensePlate())
                .setSeatCount(carDO.getSeatCount())
                .setConvertible(carDO.getConvertible())
                .setRating(carDO.getRating())
                .setEngineType(carDO.getEngineType())
                .setManufacturer(carDO.getManufacturer());

        return carDTOBuilder.createCarDTO();
    }


    public static List<CarDTO> makeCarDTOList(Collection<CarDO> cars) {
        return cars.stream()
                .map(CarMapper::makeCarDTO)
                .collect(Collectors.toList());
    }
}
