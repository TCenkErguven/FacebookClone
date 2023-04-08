package com.cenk.repository.entity;

import com.cenk.repository.EGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbluser")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long authid;
    String username;
    String email;
    String name;
    String surname;
    Long birthDate;
    String avatar;
    String address;
    String phone;
    @Enumerated(EnumType.STRING)
    EGender gender;

}
