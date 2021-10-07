package project.ssgp.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_SEARCH_TYPE(400,"Invalid Search Type"),
    INVALID_TOKEN(401,"Invalid Token"),
    EXPIRED_TOKEN(401,"Expired Token"),
    USER_NOT_FOUND(404,"User Not Found"),
    APPLICATION_NOT_FOUND(404,"Application Not Found"),
    IMAGE_NOT_FOUND(404, "Image Not Found"),
    PRODUCT_NOT_FOUND(404, "Product Not Found"),
    USER_ALREADY_EXISTS(409,"User Already Exists");

    private final int status;

    private final String message;

}
