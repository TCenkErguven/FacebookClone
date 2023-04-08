package com.cenk.repository;

import com.cenk.repository.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<UserProfile,Long> {
    List<UserProfile> findByNameStartsWithIgnoreCaseOrSurnameStartsWithIgnoreCase(String name, String surname);
}
