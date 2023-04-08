package com.cenk.dto.response;

import lombok.*;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RegisterResponseDto {
    Integer statusCode;
    String message;
}
