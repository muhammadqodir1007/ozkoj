package uz.fazo.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {

    private boolean errorEmail;

    private boolean errorPassword;

    private boolean errorVerificationCode;

    private String message;

    public RegisterResponse(boolean errorEmail, String message) {
        if (errorEmail)
            this.errorEmail = true;
        else
            this.errorPassword = true;

        this.message = message;
    }

    public RegisterResponse(String message) {
        this.message = message;
    }

    private RegisterResponse() {
        this.errorVerificationCode = true;
    }


    public static RegisterResponse wrongVerificationCode(String message) {
        RegisterResponse authResDTO = new RegisterResponse();
        authResDTO.setMessage(message);
        return authResDTO;
    }
}
