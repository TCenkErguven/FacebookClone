package com.cenk.service;

import com.cenk.repository.IYorumRepository;
import com.cenk.repository.entity.Yorum;
import com.cenk.utility.ServiceManager;
import org.springframework.stereotype.Service;

public class YorumService extends ServiceManager<Yorum,String> {
    private final IYorumRepository repository;
    public YorumService(IYorumRepository repository){
        super(repository);
        this.repository=repository;
    }
}
