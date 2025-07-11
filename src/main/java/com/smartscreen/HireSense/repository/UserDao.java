package com.smartscreen.HireSense.repository;


import com.smartscreen.HireSense.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    UserEntity findFirstByEmail(String email);
}
