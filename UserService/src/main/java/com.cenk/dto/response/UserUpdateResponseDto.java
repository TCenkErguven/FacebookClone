package com.cenk.dto.response;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateResponseDto {
    Integer statusCode;
    String message;
}
