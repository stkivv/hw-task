package stkivv.hwtask.hometask.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stkivv.hwtask.hometask.dto.CarDto;
import stkivv.hwtask.hometask.dto.UserDto;
import stkivv.hwtask.hometask.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        try {
            List<UserDto> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        try {
            UserDto user = userService.getUserById(id);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{userId}/cars")
    public ResponseEntity<List<CarDto>> getUserCars(@PathVariable Long userId) {
        try {
            List<CarDto> cars = userService.getCarsForUser(userId);
            return ResponseEntity.ok().body(cars);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
