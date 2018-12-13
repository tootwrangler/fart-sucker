package com.fart.sucker.fartsucker.repo;

import com.fart.sucker.fartsucker.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
}
