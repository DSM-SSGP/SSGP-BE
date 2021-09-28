package project.ssgp.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BasicExceptionHandler {

    @ExceptionHandler(BasicException.class)
    protected ResponseEntity<ErrorResponse> handleBasicException(final BasicException exception) {
        final ErrorCode errorCode = exception.getErrorCode();
        return new ResponseEntity<>(new ErrorResponse(errorCode.getStatus(), errorCode.getMessage()),
                HttpStatus.valueOf(errorCode.getStatus()));
    }

}
