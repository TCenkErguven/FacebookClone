package com.cenk.controller;

import com.cenk.dto.request.LoginRequestDto;
import com.cenk.dto.request.RegisterRequestDto;
import com.cenk.dto.response.LoginResponseDto;
import com.cenk.dto.response.RegisterResponseDto;
import com.cenk.exception.AuthException;
import com.cenk.exception.ErrorType;
import com.cenk.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto dto){
        if(!authService.doLogin(dto))
            return ResponseEntity.ok(LoginResponseDto.builder()
                            .statusCode(4000)
                            .message("Kullanıcı adı veya şifre hatalı")
                    .build());
        return ResponseEntity.ok(LoginResponseDto.builder()
                        .statusCode(2001)
                        .message("Giriş işlemi başarılı")
                .build());
    }
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        if(!dto.getPassword().equals(dto.getRepassword()))
            throw new AuthException(ErrorType.ERROR_PASSWORD);
        authService.register(dto);
        return ResponseEntity.ok(RegisterResponseDto.builder()
                .statusCode(200)
                .message("Kayıt işlemi başarılı bir şekilde gerçekleştir. Lütfen E-Posta " +
                        "adresinize gelen aktivasyon linkine tıklayınız.")
                .build());
    }
}
