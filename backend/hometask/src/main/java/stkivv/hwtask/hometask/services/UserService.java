package stkivv.hwtask.hometask.services;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

import stkivv.hwtask.hometask.DtoMapper;
import stkivv.hwtask.hometask.dal.UserRepository;
import stkivv.hwtask.hometask.domain.User;
import stkivv.hwtask.hometask.dto.CarDto;
import stkivv.hwtask.hometask.dto.UserDto;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> DtoMapper.mapUserToDto(u))
                .toList();
    }

    public UserDto getUserById(Long id) throws NoSuchElementException {
        User user = userRepository.findById(id).orElseThrow();
        return DtoMapper.mapUserToDto(user);
    }

    public List<CarDto> getCarsForUser(Long id) {
        UserDto user = getUserById(id);
        return user.getCars();
    }
}
