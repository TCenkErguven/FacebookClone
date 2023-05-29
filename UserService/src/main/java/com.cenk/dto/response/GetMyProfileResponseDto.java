package com.cenk.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class GetMyProfileResponseDto {
    String avatar;
    String username;
    String name;
    String about;
}
