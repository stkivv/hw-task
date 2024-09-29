package stkivv.hwtask.hometask.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import stkivv.hwtask.hometask.DtoMapper;
import stkivv.hwtask.hometask.dal.CarRepository;
import stkivv.hwtask.hometask.domain.Car;
import stkivv.hwtask.hometask.domain.enums.CarSortMethod;
import stkivv.hwtask.hometask.dto.CarDto;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDto> getAllCars() {
        return getAllCars("", CarSortMethod.ID_ASCENDING);
    }

    public List<CarDto> getAllCars(String find) {
        return getAllCars(find, CarSortMethod.ID_ASCENDING);
    }

    public List<CarDto> getAllCars(CarSortMethod sortMethod) {
        return getAllCars("", sortMethod);
    }

    public List<CarDto> getAllCars(String find, CarSortMethod sortMethod) {
        String sortField = switch (sortMethod) {
            case ID_ASCENDING, ID_DESCENDING -> "id";
            case MAKE_ASCENDING, MAKE_DESCENDING -> "make";
            case MODEL_ASCENDING, MODEL_DESCENDING -> "model";
            case NUMBER_PLATE_ASCENDING, NUMBER_PLATE_DESCENDING -> "numberPlate";
        };

        Sort.Direction direction = (sortMethod.name().contains("DESCENDING"))
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        List<Car> cars = carRepository.findByKeyword(find, Sort.by(direction, sortField));
        return cars.stream()
                .map(c -> DtoMapper.mapCarToDto(c))
                .toList();
    }

    public CarDto getCarById(Long id) throws NoSuchElementException {
        Car car = carRepository.findById(id).orElseThrow();
        return DtoMapper.mapCarToDto(car);
    }
}
