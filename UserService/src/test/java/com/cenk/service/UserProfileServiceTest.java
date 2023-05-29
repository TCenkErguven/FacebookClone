package com.cenk.service;


import com.cenk.dto.request.GetMyProfileRequestDto;
import com.cenk.dto.request.UserSaveRequestDto;
import com.cenk.dto.response.GetMyProfileResponseDto;
import com.cenk.exception.ErrorType;
import com.cenk.repository.entity.UserProfile;
import com.cenk.utility.JwtTokenManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

/**
 * Standart testlere nazaran spring test yaparken bazı anatasyonları kullanmak
 * zorundasınız.
 * @SpringBootTest
 * @ActiveProfile("test")
 */

@SpringBootTest
@TestPropertySource(locations = {"classpath:application-test.yml"})
public class UserProfileServiceTest {

    /**
     * Test taparken dikkat edilecektir,
     * 1- Her bir sınıf için ayrı bir test sınıfı oluşturmalısınız.
     * 2- Tüm methodları test etmelisiniz.
     * 2.1- ÖNEMLİ, bir method un tüm ihtimalleri için ayrı ayrı test case leri yazmalısınız.
     * 3- Test edilecek sınıfların ve methodların dış bileşenlerden ayrılarak soyutlanmış testlerinin yapılması
     * önemlidir. Böylece sadece yazdığınız kodun testini yaparsınız.
     */

    @Autowired
    UserProfileService userProfileService;
    @Autowired
    JwtTokenManager jwtTokenManager;
    @Test
    void testDeneme(){
        userProfileService.findAll().forEach(System.out::println);
    }

    @Test
    void saveTest(){
        userProfileService.save(UserSaveRequestDto.builder()
                        .authid(4L)
                        .email("bahar@mail.com")
                        .username("bahar.tekin")
                .build());
        Optional<UserProfile> user = userProfileService.findAll().stream().filter(x -> x.getUsername().equals("bahar.tekin")).findFirst();
        Assertions.assertTrue(user.isPresent());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/userProfile.csv")
    void saveCSVTest(Long authid,String email,String username){
        UserProfile userProfile = UserProfile.builder()
                .email(email)
                .username(username)
                .authid(authid)
                .build();
        UserProfile userProfileSave = userProfileService.save(userProfile);
        Assertions.assertTrue(userProfileSave.getId() != null);
    }

    @Test
    void findByIdTest(){
        Optional<UserProfile>user = userProfileService.findByAuthId(150L);
        if(user.isPresent()){

        }
    }

    @Test
    void getMyProfileInvalidTokenTest(){
       Exception exception = Assertions.assertThrows(
               Exception.class, () -> userProfileService.getMyProfile(GetMyProfileRequestDto.builder()
                        .token("23121421421421")
                .build()));
        Assertions.assertEquals(ErrorType.ERROR_INVALID_TOKEN.getMessage(),exception.getMessage());
    }

    @Test
    void getMyProfileInvalidUser(){
        Optional<String> token = jwtTokenManager.createToken(2000L);
        Exception exception = Assertions.assertThrows(
                Exception.class, () -> userProfileService.getMyProfile(GetMyProfileRequestDto.builder()
                        .token(token.get())
                        .build()));
        Assertions.assertEquals(ErrorType.USER_NOT_FOUND.getMessage(),exception.getMessage());
    }

    @Test
    void getMyProfileTest(){
        Optional<String> token = jwtTokenManager.createToken(1L);
        GetMyProfileResponseDto responseDto = userProfileService.getMyProfile(GetMyProfileRequestDto.builder()
                .token(token.get()).build());
        Assertions.assertTrue(responseDto.getUsername() != null);
    }
}
