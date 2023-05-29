package com.cenk.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class UserProfile {
    @Id
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
