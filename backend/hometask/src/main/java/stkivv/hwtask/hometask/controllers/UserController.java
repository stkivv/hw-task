package stkivv.hwtask.hometask.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import stkivv.hwtask.hometask.domain.enums.UserSortMethod;
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
    public ResponseEntity<List<UserDto>> getAll(@RequestParam(required = false) String find,
            @RequestParam(required = false) UserSortMethod sort) {
        try {
            List<UserDto> users;
            if (find != null && sort != null) {
                users = userService.getAllUsers(find, sort);
            } else if (find != null) {
                users = userService.getAllUsers(find);
            } else if (sort != null) {
                users = userService.getAllUsers(sort);
            } else {
                users = userService.getAllUsers();
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        try {
            UserDto user = userService.getUserById(id);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{userId}/cars")
    public ResponseEntity<List<CarDto>> getUserCars(@PathVariable Long userId) {
        try {
            List<CarDto> cars = userService.getCarsForUser(userId);
            return ResponseEntity.ok().body(cars);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
