package stkivv.hwtask.hometask.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import stkivv.hwtask.hometask.DtoMapper;
import stkivv.hwtask.hometask.dal.UserRepository;
import stkivv.hwtask.hometask.domain.User;
import stkivv.hwtask.hometask.domain.enums.UserSortMethod;
import stkivv.hwtask.hometask.dto.CarDto;
import stkivv.hwtask.hometask.dto.UserDto;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        return getAllUsers("", UserSortMethod.ID_ASCENDING);
    }

    public List<UserDto> getAllUsers(UserSortMethod sortMethod) {
        return getAllUsers("", sortMethod);
    }

    public List<UserDto> getAllUsers(String find) {
        return getAllUsers(find, UserSortMethod.ID_ASCENDING);
    }

    public List<UserDto> getAllUsers(String find, UserSortMethod sortMethod) {
        String sortField = switch (sortMethod) {
            case ID_ASCENDING, ID_DESCENDING -> "id";
            case NAME_ASCENDING, NAME_DESCENDING -> "name";
        };

        Sort.Direction direction = (sortMethod.name().contains("DESCENDING"))
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        List<User> users = userRepository.findByNameContaining(find, Sort.by(direction, sortField));
        return users.stream()
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
