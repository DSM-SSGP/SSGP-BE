package project.ssgp.exception;

import project.ssgp.error.BasicException;
import project.ssgp.error.ErrorCode;

public class UserAlreadyExistsException extends BasicException {

    public UserAlreadyExistsException() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }

}
