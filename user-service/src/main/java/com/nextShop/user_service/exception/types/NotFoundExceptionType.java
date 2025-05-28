package com.nextShop.user_service.exception.types;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotFoundExceptionType {

    private String target;
    private Map<String, Object> fields;

    public static NotFoundExceptionType of(String target, Map<String, Object> fields) {
        return NotFoundExceptionType.builder()
                .fields(fields)
                .target(target)
                .build();
    }

}
