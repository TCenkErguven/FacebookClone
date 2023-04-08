package com.cenk.dto.response;

import lombok.*;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    Integer statusCode;
    String message;
}
