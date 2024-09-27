package stkivv.hwtask.hometask;

import java.util.List;

import stkivv.hwtask.hometask.domain.Car;
import stkivv.hwtask.hometask.domain.User;
import stkivv.hwtask.hometask.dto.CarDto;
import stkivv.hwtask.hometask.dto.UserDto;

public final class DtoMapper {

    private DtoMapper() {
    }

    public static UserDto mapUserToDto(User user) {
        List<CarDto> userCars = user.getCars().stream().map(c -> mapCarToDto(c)).toList();
        return new UserDto(user.getId(), user.getName(), userCars);
    }

    public static CarDto mapCarToDto(Car car) {
        return new CarDto(car.getId(), car.getMake(), car.getModel(), car.getNumberPlate());
    }
}
