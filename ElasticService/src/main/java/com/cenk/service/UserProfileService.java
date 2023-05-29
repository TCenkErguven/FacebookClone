package com.cenk.service;

import com.cenk.dto.request.UserProfileDto;
import com.cenk.mapper.IUserProfileMapper;
import com.cenk.repository.IUserProfileRepository;
import com.cenk.repository.entity.UserProfile;
import com.cenk.utility.ServiceManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String> {
    private final IUserProfileRepository repository;
    public UserProfileService(IUserProfileRepository repository){
        super(repository);
        this.repository = repository;
    }

    public void save(UserProfileDto dto){
        UserProfile user = IUserProfileMapper.INSTANCE.toUserProfile(dto);
        save(user);
    }

    /**
     * Sayfalama işlemleri;
     * Bir kayıt bilgisinde çok fazla datanın olması nedeniyle bilgilerin belli
     * bir sayfalama yöntemi ile çağrılması.
     * Hangi Sayfadasın? Page
     * Bir Sayfada Kaç Kayıt Var? Size
     * Gösterdiğin Kayıtları Sıralayacak mısın? Sort
     */
    public Page<UserProfile> findAll(int currentPage, int size, String sortParameter, String sortDirection){
        /**
         * Sort ->  Sıralama işlemlerini belirtir.
         * Pageable -> Sayfalama işlemlerini belirtir.
         */
        Sort sort = null;
        Pageable pageable = null;

        if(sortParameter != null)
            sort = Sort.by(Sort.Direction.fromString(sortDirection == null ? "ASC" : sortDirection), sortParameter);

        if(sort != null && currentPage != 0){
            pageable = PageRequest.of(currentPage,size == 0 ? 10 : size,sort);
        } else{
            pageable = PageRequest.of(currentPage, size == 0 ? 10 : size);
        }
        return repository.findAll(pageable);
    }


}
