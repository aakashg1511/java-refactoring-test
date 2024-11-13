package com.sap.refactoring.repository;

import com.sap.refactoring.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SapUserRepository extends JpaRepository<UserEntity,String> {

    @Query(value= "SELECT * FROM Users", nativeQuery=true)
    List<UserEntity> getUsers();

    @Query(value ="SELECT * FROM Users where name like ?1%", nativeQuery = true)
    List<UserEntity> searchUsers(String name);

    @Query(value ="SELECT * FROM Users where name=?1", nativeQuery = true)
    UserEntity findbyEmail( String email);

    String deleteByEmail(String email);
}