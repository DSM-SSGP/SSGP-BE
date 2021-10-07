package project.ssgp.exception;

import project.ssgp.error.BasicException;
import project.ssgp.error.ErrorCode;

public class ProductNotFoundException extends BasicException {

    public ProductNotFoundException() {
        super(ErrorCode.PRODUCT_NOT_FOUND);
    }

}
