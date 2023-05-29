package com.cenk.repository;

import com.cenk.repository.entity.YorumBegeni;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IYorumBegeniRepository extends MongoRepository<YorumBegeni,String> {
}
