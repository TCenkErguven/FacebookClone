package com.cenk.utility;

import com.cenk.manager.IElasticServiceManager;
import com.cenk.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/***
 * Component olarak işaretlenen bu sınıf, Spring Context'i başlatıldığında çalışır.
 */
@Component
@RequiredArgsConstructor
public class Runner {
    private final UserProfileService userProfileService;
    private final IElasticServiceManager elasticServiceManager;

    /**
     * PostConstruct anatasyonu ile işaretlenen bu method bu sınıf tan bir nesne oluşturulurken çalışır.
     * Böylece spring uygulaması ayağa kalkarken bu method çalışır.
     */
    //@PostConstruct
    public void init(){
        System.out.println("RUNNERIM VE ÇALIŞIYORUM");
        userProfileService.findAll().forEach(x->{
            elasticServiceManager.save(x);
        });
    }
}
