package com.nextShop.product.exceprion;

import com.nextShop.product.entity.enums.response.ErrorResponseMessages;
import com.nextShop.product.entity.enums.response.ResponseMessage;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseException extends RuntimeException {

    ResponseMessage responseMessage;

    // ToDo: make it dynamic
    @Override
    public String getMessage() {
        return responseMessage.message();
    }

    public static BaseException unexpected() {
        return new BaseException(ErrorResponseMessages.UNEXPECTED);
    }

}
