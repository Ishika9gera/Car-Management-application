package com.ishika.controller.mapper;

import com.ishika.datatransferobject.DriverDTO;
import com.ishika.domainobject.DriverDO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DriverMapper {
    public static DriverDO makeDriverDO(DriverDTO driverDTO) {
        return new DriverDO(driverDTO.getUsername(), driverDTO.getPassword());
    }


    public static DriverDTO makeDriverDTO(DriverDO driverDO) {
        DriverDTO.DriverDTOBuilder driverDTOBuilder = DriverDTO.newBuilder()
                .setId(driverDO.getId())
                .setPassword(driverDO.getPassword())
                .setUsername(driverDO.getUsername());

        if (driverDO.getCoordinate() != null) {
            driverDTOBuilder.setCoordinate(driverDO.getCoordinate());
        }

        return driverDTOBuilder.createDriverDTO();
    }


    public static DriverDTO makeDriverInfoDTO(DriverDO driverDO) {
        DriverDTO.DriverDTOBuilder driverDTOBuilder = DriverDTO.newBuilder()
                .setId(driverDO.getId())
                .setUsername(driverDO.getUsername());

        if (driverDO.getCoordinate() != null) {
            driverDTOBuilder.setCoordinate(driverDO.getCoordinate());
        }
        if (driverDO.getSelectedCar() != null) {
            driverDTOBuilder.setSelectedCar(driverDO.getSelectedCar());
        }

        return driverDTOBuilder.createDriverDTO();
    }


    public static List<DriverDTO> makeDriverDTOList(Collection<DriverDO> drivers) {
        return drivers.stream()
                .map(DriverMapper::makeDriverInfoDTO)
                .collect(Collectors.toList());
    }
}
