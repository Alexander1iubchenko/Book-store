package validation;

import dto.user.UserRegistrationRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        UserRegistrationRequestDto requestDto = (UserRegistrationRequestDto) object;
        return requestDto.getPassword().equals(requestDto.getRepeatPassword());
    }
}
