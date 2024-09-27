package stkivv.hwtask.hometask.services;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

import stkivv.hwtask.hometask.DtoMapper;
import stkivv.hwtask.hometask.dal.CarRepository;
import stkivv.hwtask.hometask.domain.Car;
import stkivv.hwtask.hometask.dto.CarDto;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDto> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(c -> DtoMapper.mapCarToDto(c))
                .toList();
    }

    public CarDto getCarById(Long id) throws NoSuchElementException {
        Car car = carRepository.findById(id).orElseThrow();
        return DtoMapper.mapCarToDto(car);
    }
}
