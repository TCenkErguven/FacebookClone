package com.cenk.mapper;

import com.cenk.dto.request.UserSaveRequestDto;
import com.cenk.dto.response.FindResponseDto;
import com.cenk.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);
    FindResponseDto toFindResponseDto(final UserProfile user);
    UserProfile toUser(final UserSaveRequestDto dto);
}
