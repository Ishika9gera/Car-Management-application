package com.ishika.dataaccessobject;

import com.ishika.domainobject.CarDO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Database Access Object for car table.
 * <p/>
 */
public interface CarRepository extends CrudRepository<CarDO, Long> {

    List<CarDO> findByAvailable(Boolean available);

    List<CarDO> findAll();

    List<CarDO> findAllByLicensePlateContainsOrSeatCountIsGreaterThanEqual(String licensePlate, Integer seatCount);

    List<CarDO> findAllByManufacturerIsStartingWith(String manufacturer);

}
