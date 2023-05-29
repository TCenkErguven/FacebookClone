package com.cenk.repository;

import com.cenk.repository.entity.KayitliPostlar;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IKayitliPostlarRepository extends MongoRepository<KayitliPostlar,String> {
}
