package stkivv.hwtask.hometask.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import stkivv.hwtask.hometask.domain.enums.CarSortMethod;
import stkivv.hwtask.hometask.dto.CarDto;
import stkivv.hwtask.hometask.services.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getAll(@RequestParam(required = false) String find,
            @RequestParam(required = false) CarSortMethod sort) {
        try {
            List<CarDto> cars;
            if (find != null && sort != null) {
                cars = carService.getAllCars(find, sort);
            } else if (find != null) {
                cars = carService.getAllCars(find);
            } else if (sort != null) {
                cars = carService.getAllCars(sort);
            } else {
                cars = carService.getAllCars();
            }
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        try {
            CarDto car = carService.getCarById(id);
            return ResponseEntity.ok().body(car);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
