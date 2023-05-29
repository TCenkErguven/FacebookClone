package com.cenk.repository;

import com.cenk.repository.entity.PostResimPozisyon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostResimPozisyonRepository extends MongoRepository<PostResimPozisyon,String> {
}
