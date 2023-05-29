package com.cenk.service;

import com.cenk.repository.IKayitliPostlarRepository;
import com.cenk.repository.entity.KayitliPostlar;
import com.cenk.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class KayitliPostlarService extends ServiceManager<KayitliPostlar,String> {
    private final IKayitliPostlarRepository repository;
    public KayitliPostlarService(IKayitliPostlarRepository repository){
        super(repository);
        this.repository = repository;
    }
}
