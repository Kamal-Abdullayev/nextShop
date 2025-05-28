package com.nextShop.user_service.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nextShop.user_service.exception.types.NotFoundExceptionType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;


@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    HttpStatus status;
    Meta meta;
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

        public static Meta of(ResponseMessages responseMessages) {
            return of(responseMessages.key(), responseMessages.message());
        }

        public static Meta of(BaseException ex) {
            if (ex.getResponseMessage().equals(ErrorResponseMessages.NOT_FOUND)) {
                NotFoundExceptionType notFoundData = ex.getNotFoundData();

                return of(
                        String.format(ex.getResponseMessage().key(), notFoundData.getTarget().toLowerCase()),
                        String.format(ex.getResponseMessage().message(), notFoundData.getTarget(), notFoundData.getFields().toString())
                );
            }

            return of(ex.getResponseMessage());
        }
    }

    public static <T> BaseResponse<T> success(T data) {
        return BaseResponse.<T>builder()
                .status(HttpStatus.OK)
                .data(data)
                .meta(Meta.of(SuccessResponseMessages.SUCCESS))
                .build();
    }

    public static <T> BaseResponse<T> success() {
        return success(null);
    }

    public static <T> BaseResponse<T> created(T data) {
        return BaseResponse.<T>builder()
                .status(HttpStatus.CREATED)
                .data(data)
                .meta(Meta.of(SuccessResponseMessages.CREATED))
                .build();
    }
    public static <T> BaseResponse<T> created() {
        return created(null);
    }
    public static BaseResponse<?> error(BaseException ex) {
        return BaseResponse.builder()
                .meta(Meta.of(ex))
                .status(ex.getResponseMessage().status())
                .build();
    }

}
