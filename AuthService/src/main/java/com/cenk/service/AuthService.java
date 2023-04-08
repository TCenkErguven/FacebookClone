package com.cenk.service;

import com.cenk.dto.request.LoginRequestDto;
import com.cenk.dto.request.RegisterRequestDto;
import com.cenk.dto.request.UserSaveRequestDto;
import com.cenk.exception.AuthException;
import com.cenk.exception.ErrorType;
import com.cenk.manager.IUserProfileManager;
import com.cenk.mapper.IAuthMapper;
import com.cenk.repository.IAuthRepository;
import com.cenk.repository.entity.Auth;
import com.cenk.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    private final IUserProfileManager userProfileManager;

    public AuthService(IAuthRepository repository,
                       IUserProfileManager userProfileManager){
        super(repository);
        this.repository = repository;
        this.userProfileManager = userProfileManager;
    }

    public boolean doLogin(LoginRequestDto dto){
        Optional<Auth> auth = repository.findOptionalByUsernameAndPassword(
                dto.getUsername(),dto.getPassword()
        );
        if(auth.isEmpty()) return false;
        return true;
    }

    public void register(RegisterRequestDto dto){
        if(repository.existsByUsername(dto.getUsername()))
            throw new AuthException(ErrorType.ERROR_USERNAME);
        /**
         * save methodu kayı işleminden sonra bize auth nesnesini dönmektedir.
         */
        Auth auth = save(IAuthMapper.INSTANCE.toAuth(dto));
        /**
         * Bir kullanıcı uygulamamızda üyelik açtıktan sonra bu kullanıcıya ait
         * bilgiler ile userprofil bilgisinin de oluşturulması gerekiyor. Bunu sağlamak için
         * UserProfile servisine istek atmak üzere FeignClient kullanıyoruz.
         * Kaydetme işlemi için, manager bizden DTO istemektedir. Bu nedenle
         * auth için yapılan kayıt bilgilerini dto'nun içine koyarak istek atıyoruz.
         */
        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .username(auth.getUsername())
                .email(auth.getEmail())
                .authid(auth.getId())
                .build();
        /**
         * Bu kısımda, DTO içindeki alanlara gerekli olan datalar girilir. FeignClient bizim için
         * verdiğimiz parametreleri iletişim geçeceğimiz UserProfile servisinin save methoduna
         * jsonObject olarak gönderir ve böylece o save methodunun çalışmasını sağlar.
         */
        userProfileManager.save(requestDto);
    }

}
