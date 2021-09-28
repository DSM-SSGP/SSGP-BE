package project.ssgp.exception;

import project.ssgp.error.BasicException;
import project.ssgp.error.ErrorCode;

public class ExpiredTokenException extends BasicException {
    public ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
