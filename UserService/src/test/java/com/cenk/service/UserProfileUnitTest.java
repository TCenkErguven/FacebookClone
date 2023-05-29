package com.cenk.service;

import com.cenk.dto.request.GetMyProfileRequestDto;
import com.cenk.dto.response.GetMyProfileResponseDto;
import com.cenk.repository.IUserRepository;
import com.cenk.repository.entity.UserProfile;
import com.cenk.utility.JwtTokenManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserProfileUnitTest {

    @InjectMocks
    UserProfileService userProfileService;
    @Mock
    IUserRepository userRepository;
    @Mock
    JwtTokenManager jwtTokenManager;

    @Test
    void getMyProfileTest(){
        when(jwtTokenManager.getIdFromToken(any()))
                .thenReturn(Optional.of(1l));
        when(userRepository.findOptionalByAuthid(any()))
                .thenReturn(Optional.of(
                        UserProfile.builder()
                                .phone("0 555 666")
                                .avatar("")
                                .name("Muhammet")
                                .surname("HOCA")
                                .username("muhammet")
                                .build()
                ));
        GetMyProfileResponseDto responseDto =
                userProfileService.getMyProfile(GetMyProfileRequestDto.builder()
                        .token("12321353fasfas32132")
                .build());
        Assertions.assertTrue(responseDto != null);
    }

}
