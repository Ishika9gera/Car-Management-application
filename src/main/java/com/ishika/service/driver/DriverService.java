package com.ishika.service.driver;

import com.ishika.domainobject.CarDO;
import com.ishika.domainobject.DriverDO;
import com.ishika.domainvalue.OnlineStatus;
import com.ishika.exception.ConstraintsViolationException;
import com.ishika.exception.EntityNotFoundException;

import java.util.List;

public interface DriverService {

    DriverDO find(Long driverId) throws EntityNotFoundException;

    DriverDO findOnline(long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDO> find(OnlineStatus onlineStatus);

    void updateSelectedCar(long driverId, CarDO carDO) throws EntityNotFoundException;

}
