package com.cenk.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblauth")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotEmpty
    @Size(min = 3)
    String username;
    @NotBlank
    @Size(min = 8)
    String password;
    @Email
    String email;
    /***
     * Kullanıcının kayıt edilme tarihini tutan değer. lONG TUTAR
     */
    Long creatat;
    /**
     * 0-Kullanıcı kayıt edilmiş ama onaylanmamış
     * 1-Kullanıcı kayıt edilmiş ve onaylanmış
     * 2-Kullanıcı kayıt edilmiş ve onaylanmış fakat hesabı kilitlenmiş
     * 3-Kulanıcı kayıt edilmiş ve onaylaşnmış fakat hesabı silinmiş
     */
    int status;
    /**
     * Kullanıcının durumu yukarıdaki değişken ile tutabileceği gibi
     * bir enum ile de tutulabilir. Aşağıda tanımlanan Enum içinde,
     * Aktif,Pasif ve Silinmiş durumları tutabiliriz.
     */
//    @Enumerated(EnumType.STRING)
//    State state;
}
