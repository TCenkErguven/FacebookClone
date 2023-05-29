package com.cenk.dto.request;

import com.cenk.repository.entity.EGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserProfileDto {
    String id;
    Long authid;
    String username;
    String email;
    String name;
    String surname;
    Long birthDate;
    String avatar;
    String address;
    String phone;
    EGender gender;
}
