package com.cenk.service;

import com.cenk.dto.request.UserFindRequestDto;
import com.cenk.dto.request.*;
import com.cenk.dto.response.FindResponseDto;
import com.cenk.exception.ErrorType;
import com.cenk.exception.UserException;
import com.cenk.mapper.IUserMapper;
import com.cenk.repository.IUserRepository;
import com.cenk.repository.entity.UserProfile;
import com.cenk.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserRepository iUserRepository;

    public UserProfileService(IUserRepository iUserRepository){
        super(iUserRepository);
        this.iUserRepository = iUserRepository;
    }

    public void save(UserSaveRequestDto dto){
        save(IUserMapper.INSTANCE.toUser(dto));
    }

    public List<FindResponseDto> findNameOrSurname(UserFindRequestDto dto){
        List<UserProfile> users = iUserRepository.findByNameStartsWithIgnoreCaseOrSurnameStartsWithIgnoreCase(
                dto.getName(),dto.getName());
        if(users.isEmpty())
            throw new UserException(ErrorType.ERROR_INVALID_TOKEN);
        List<FindResponseDto> foundUserlist = new ArrayList<>();
        users.forEach(user -> {
            foundUserlist.add(IUserMapper.INSTANCE.toFindResponseDto(user));
        });
        return foundUserlist;
    }
    public Boolean updateUser(UserUpdateRequestDto dto){
        Boolean status = false;
        Optional<UserProfile> userOptional = iUserRepository.findById(dto.getId());
        if(userOptional.isPresent()){
             userOptional.get().setAddress(dto.getAddress());
             userOptional.get().setPhone(dto.getPhone());
             userOptional.get().setAvatar(dto.getAvatar());
             iUserRepository.save(userOptional.get());
             status = true;
        }
        return status;
    }
}
