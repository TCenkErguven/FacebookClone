package com.cenk.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class GetPostResponseDto {
    String useravatar;
    String username;
    List<String> posturls;
    int likecount;
    String sharedtime;
    String posttext;
}
