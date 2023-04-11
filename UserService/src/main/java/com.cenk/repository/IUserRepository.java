package com.cenk.repository;

import com.cenk.repository.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<UserProfile,String> {
    List<UserProfile> findByNameStartsWithIgnoreCaseOrSurnameStartsWithIgnoreCase(String name, String surname);
    Optional<UserProfile> findOptionalByAuthid(Long authid);
}
