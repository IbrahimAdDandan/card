package com.example.smartcard.repository;

import com.example.smartcard.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUsername(String username);
}
