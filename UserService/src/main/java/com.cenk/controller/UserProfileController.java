package com.cenk.controller;


import com.cenk.dto.request.*;
import com.cenk.dto.response.*;
import com.cenk.exception.ErrorType;
import com.cenk.exception.UserException;
import com.cenk.repository.entity.UserProfile;
import com.cenk.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/userprofile")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userService;

    @GetMapping("/getpage")
    public ResponseEntity<String> getPage(){
        return ResponseEntity.ok("User Service Ulaştınız.");
    }

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
    public ResponseEntity<Void> update(@RequestBody @Valid UserUpdateRequestDto dto){
        userService.update(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/findall")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN') or hasAnyAuthority(NE_OLA_KI)")
    public ResponseEntity<List<UserProfile>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/getnametoupper")
    public ResponseEntity<String> getNameToUpper(String name){
        return ResponseEntity.ok(userService.getNameToUpper(name));
    }

    @GetMapping("/clearcache")
    public ResponseEntity<Void> clearCache(){
        userService.clearCacheToUpper();
        return ResponseEntity.ok().build();
    }

    @CrossOrigin("*")
    @PostMapping("/getmyprofile")
    public ResponseEntity<GetMyProfileResponseDto> getMyProfile(@RequestBody @Valid GetMyProfileRequestDto dto){
        return ResponseEntity.ok(userService.getMyProfile(dto));
    }

    @CrossOrigin("*")
    @PostMapping("/getotherprofile")
    public ResponseEntity<UserProfile> getOtherProfile(@RequestBody @Valid GetMyProfileRequestDto dto){
        return ResponseEntity.ok(userService.getOtherProfile(dto));
    }

}
