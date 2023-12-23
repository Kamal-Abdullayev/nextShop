package com.nextShop.product.entity.base;

import com.nextShop.product.entity.enums.response.ResponseMessage;
import com.nextShop.product.entity.enums.response.SuccessResponseMessage;
import com.nextShop.product.exceprion.BaseException;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PROTECTED)
public class BaseResponse<T> {
    HttpStatus status;
    Meta meta;
    String message;
    T data;

    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder(access = AccessLevel.PRIVATE)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static final class Meta {
        String key;
        String message;

        public static Meta of(String key, String message) {
            return Meta.builder()
                    .key(key)
                    .message(message)
                    .build();
        }
        public static Meta of(ResponseMessage responseMessage) {
            return of(responseMessage.key(), responseMessage.key());
        }
    }

    public static <T> BaseResponse<T> success(T data) {
        return BaseResponse.<T>builder()
                .status(HttpStatus.OK)
                .message("Success")
                .meta(Meta.of(SuccessResponseMessage.SUCCESS))
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> success() {return success(null);}
    public static BaseResponse<?> error(BaseException ex) {
        return BaseResponse.builder()
                .meta(Meta.of(ex.getResponseMessage()))
                .status(ex.getResponseMessage().status())
                .build();
    }

}
