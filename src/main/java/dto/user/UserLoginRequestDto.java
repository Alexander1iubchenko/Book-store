package dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public record UserLoginRequestDto(

        @NotEmpty
        @Size(min = 8, max = 20)
        @Email
        String email,

        @NotEmpty
        @Size(min = 8, max = 20)
        String password
) {

}
