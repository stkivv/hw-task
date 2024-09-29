package stkivv.hwtask.hometask.domain.enums;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CarSortMethodConverter implements Converter<String, CarSortMethod> {
    @Override
    public CarSortMethod convert(String value) {
        if (value.equals("id:desc")) {
            return CarSortMethod.ID_DESCENDING;
        }
        if (value.equals("make:asc")) {
            return CarSortMethod.MAKE_ASCENDING;
        }
        if (value.equals("make:desc")) {
            return CarSortMethod.MAKE_DESCENDING;
        }
        if (value.equals("model:asc")) {
            return CarSortMethod.MODEL_ASCENDING;
        }
        if (value.equals("model:desc")) {
            return CarSortMethod.MODEL_DESCENDING;
        }
        if (value.equals("nrplate:asc")) {
            return CarSortMethod.NUMBER_PLATE_ASCENDING;
        }
        if (value.equals("nrplate:desc")) {
            return CarSortMethod.NUMBER_PLATE_DESCENDING;
        }
        return CarSortMethod.ID_ASCENDING;
    }
}
