package project.ssgp.exception;

import project.ssgp.error.BasicException;
import project.ssgp.error.ErrorCode;

public class UserNotFoundException extends BasicException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }

}
