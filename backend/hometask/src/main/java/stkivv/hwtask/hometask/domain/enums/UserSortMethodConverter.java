package stkivv.hwtask.hometask.domain.enums;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserSortMethodConverter implements Converter<String, UserSortMethod> {
    @Override
    public UserSortMethod convert(String value) {
        if (value.equals("name:asc")) {
            return UserSortMethod.NAME_ASCENDING;
        }
        if (value.equals("name:desc")) {
            return UserSortMethod.NAME_DESCENDING;
        }
        if (value.equals("id:desc")) {
            return UserSortMethod.ID_DESCENDING;
        }
        return UserSortMethod.ID_ASCENDING;
    }
}
