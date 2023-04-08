package com.cenk.dto.request;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFindRequestDto {
    String name;
}
