package com.cenk.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    @NotEmpty
    @Size(min = 3)
    String username;
    @NotBlank
    @Size(min = 8)
    String password;
}
