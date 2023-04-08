package com.cenk.controller;


import com.cenk.dto.request.*;
import com.cenk.dto.response.*;
import com.cenk.exception.ErrorType;
import com.cenk.exception.UserException;
import com.cenk.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/userprofile")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userService;

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody @Valid UserSaveRequestDto dto){
        userService.save(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find-user")
    public ResponseEntity<List<FindResponseDto>> findUser(UserFindRequestDto dto){
        if(dto.getName().trim().equals("")){
            throw new UserException(ErrorType.USER_NOT_FOUND);
        }
        return ResponseEntity.ok(userService.findNameOrSurname(dto));
    }

    @PostMapping("/update")
    public ResponseEntity<UserUpdateResponseDto> update(@RequestBody @Valid UserUpdateRequestDto dto){
        Boolean status = userService.updateUser(dto);
        if(status) {
            return ResponseEntity.ok(UserUpdateResponseDto.builder()
                    .statusCode(200)
                    .message("Güncelleme işlemi başarılı bir şekilde gerçekleşti, Helal be sana")
                    .build());
        }
        return ResponseEntity.ok(UserUpdateResponseDto.builder()
                .statusCode(300)
                .message("Güncelleme işlemi başarısız, Yuh be Çüş be")
                .build());
    }


}
