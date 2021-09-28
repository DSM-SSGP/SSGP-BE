package project.ssgp.exception;

import project.ssgp.error.BasicException;
import project.ssgp.error.ErrorCode;

public class InvalidTokenException extends BasicException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
