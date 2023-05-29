package com.cenk.service;

import com.cenk.repository.IPostResimPozisyonRepository;
import com.cenk.repository.entity.PostResimPozisyon;
import com.cenk.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class PostResimPozisyonService extends ServiceManager<PostResimPozisyon,String> {
    private final IPostResimPozisyonRepository repository;
    public PostResimPozisyonService(IPostResimPozisyonRepository repository){
        super(repository);
        this.repository = repository;
    }
}
