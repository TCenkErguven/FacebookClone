package com.cenk.service;

import com.cenk.repository.IBegeniRepository;
import com.cenk.repository.entity.Begeni;
import com.cenk.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class BegeniService extends ServiceManager<Begeni,String> {
    private final IBegeniRepository repository;
    public BegeniService(IBegeniRepository repository){
        super(repository);
        this.repository = repository;
    }
}
