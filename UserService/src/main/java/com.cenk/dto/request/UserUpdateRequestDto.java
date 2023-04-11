package com.cenk.dto.request;

import com.cenk.repository.EGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDto {
    @NotEmpty
    @Size(min = 5, max = 255)
    String token;
    String name;
    String surname;
    String avatar;
    String address;
    String phone;
    EGender gender;

}
