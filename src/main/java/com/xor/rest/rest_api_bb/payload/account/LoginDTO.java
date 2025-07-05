package com.xor.rest.rest_api_bb.payload.account;

import com.xor.rest.rest_api_bb.utils.generic.InputValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginDTO {
    public String usernameOrEmail;
    public String password;

    public Boolean isUsernameOrEmail() {
        return InputValidator.isEmail(this.usernameOrEmail);
    }

}
