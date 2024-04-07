package com.ishika.service.car;

import com.ishika.dataaccessobject.CarRepository;
import com.ishika.domainobject.CarDO;
import com.ishika.exception.CarAlreadyInUseException;
import com.ishika.exception.ConstraintsViolationException;
import com.ishika.exception.EntityNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some car specific things.
 * <p/>
 */
@Service
public class DefaultCarService implements CarService {

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultCarService.class);

    @Autowired
    private CarRepository carRepository;


    /**
     * Selects a car by id.
     *
     * @param carId
     * @return found car
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    public CarDO find(Long carId) throws EntityNotFoundException {
        return findCarChecked(carId);
    }


    /**
     * Creates a new car.
     *
     * @param carDO
     * @return
     * @throws ConstraintsViolationException if a car already exists.
     */
    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException {
        CarDO car;
        try {
            car = carRepository.save(carDO);
        } catch (DataIntegrityViolationException e) {
            LOG.warn("Some constraints are thrown due to car creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return car;
    }


    /**
     * Deletes an existing car by id.
     *
     * @param carId
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long carId) throws EntityNotFoundException {
        CarDO carDO = findCarChecked(carId);
        carDO.setActive(false);
    }


    /**
     * Select a car.
     *
     * @param carId
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public CarDO selectCar(long carId) throws EntityNotFoundException, CarAlreadyInUseException {
        CarDO carDO = findCarChecked(carId);
        if (checkCarAvailability(carDO)) {
            carDO.setAvailable(false);
        } else {
            throw new CarAlreadyInUseException("Selected car is not available at the moment!");
        }
        return carDO;
    }


    /**
     * Drop a car.
     *
     * @param carId
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void dropSelectedCar(long carId) throws EntityNotFoundException {
        CarDO carDO = findCarChecked(carId);
        if (!carDO.getAvailable()) {
            carDO.setAvailable(true);
        } else {
            throw new EntityNotFoundException("Selected car was not actually in use!");
        }
    }


    /**
     * Find all cars by availability.
     *
     * @param available
     */
    @Override
    public List<CarDO> find(Boolean available) {
        return carRepository.findByAvailable(true);
    }


    /**
     * Find all cars
     */
    @Override
    public List<CarDO> findAll() {
        return carRepository.findAll();
    }


    /**
     * Find all cars
     */
    private boolean checkCarAvailability(CarDO carDO) {
        if (carDO.getAvailable() != null && carDO.getAvailable()) {
            return true;
        } else {
            return false;
        }
    }


    private CarDO findCarChecked(Long carId) throws EntityNotFoundException {
        return carRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
    }


}
