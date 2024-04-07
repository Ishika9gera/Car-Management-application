package com.ishika.service.car;

import com.ishika.domainobject.CarDO;
import com.ishika.exception.CarAlreadyInUseException;
import com.ishika.exception.ConstraintsViolationException;
import com.ishika.exception.EntityNotFoundException;

import java.util.List;

public interface CarService {

    CarDO find(Long carId) throws EntityNotFoundException;

    CarDO create(CarDO carDO) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

    CarDO selectCar(long carId) throws EntityNotFoundException, CarAlreadyInUseException;

    void dropSelectedCar(long carId) throws EntityNotFoundException;

    List<CarDO> find(Boolean available);

    List<CarDO> findAll();


}
