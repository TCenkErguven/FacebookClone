package com.cenk.dto.response;

import lombok.*;
import org.hibernate.validator.constraints.URL;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindResponseDto {

    String name;
    String surname;
    Long birthDate;
    //@URL
    String imageUrl;

}
