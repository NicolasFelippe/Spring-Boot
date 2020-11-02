package com.example.apirest.repository;

import com.example.apirest.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

    @Query("select u from User u where u.login in :login")
    UserModel findByUserLogin(String login);


    @Query("select u from User u where u.login in :login and u.password in :password")
    UserModel findByUserLoginAndPassword(String login, String password);
}
