package com.cenk.service;

import com.cenk.repository.IPostResimRepository;
import com.cenk.repository.entity.PostResim;
import com.cenk.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostResimService extends ServiceManager<PostResim,String> {
    private final IPostResimRepository repository;
    public PostResimService(IPostResimRepository repository){
        super(repository);
        this.repository = repository;
    }

    public List<String> getUrlsByPostId(String postId){
        /**
         * Burada resimlerin listesi string olarak dönmüyor.
         * Entity olarak datalar dönüyor. Bu nedenle buradaki
         * bilgileri bir String listesi haline çevirmemiz gerekli.
         */
        List<PostResim> postResimList = repository.findAllByPostid(postId);
        /**
         * Bir adet boş String listesi oluşturdum.
         */
        List<String> urlList = new ArrayList<>();
        /**
         * Ilgili post'a ait resim listesi entity sini foreach ile dönerek
         * oluşturduğum listeye resimlerin url lerini tek tek ekledim.
         */
        postResimList.forEach(x->{
            urlList.add(x.getUrl());
        });
        return urlList;
    }
}
