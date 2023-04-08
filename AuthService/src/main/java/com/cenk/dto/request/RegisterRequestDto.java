package com.cenk.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {
    @NotEmpty
    @Size(min = 3)
    String username;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$",
    message = "Şifre en az bir büyük, bir küçük, harf, rakam, ve özel harf kullanınız")
    @Size(min = 8)
    String password;
    String repassword;
    String email;
}
