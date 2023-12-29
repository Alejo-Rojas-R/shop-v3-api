package com.practice.shopv3api.repositories;

import com.practice.shopv3api.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @Query
    Optional<UserEntity> findByEmail(String email);
}
