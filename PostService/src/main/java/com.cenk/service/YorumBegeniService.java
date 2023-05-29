package com.cenk.service;

import com.cenk.repository.IYorumBegeniRepository;
import com.cenk.repository.entity.YorumBegeni;
import com.cenk.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class YorumBegeniService extends ServiceManager<YorumBegeni,String> {
    private final IYorumBegeniRepository repository;
    public YorumBegeniService(IYorumBegeniRepository repository){
        super(repository);
        this.repository = repository;
    }
}
