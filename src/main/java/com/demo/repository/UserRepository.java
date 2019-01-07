package com.demo.repository;

import com.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select t from User t where t.username = :username")
    User findByUserName(@Param("username") String username);

    @Query("select t from User t where t.id = :id")
    User findById(@Param("id") String id);

    @Query("select t from User t where t.role = :role")
    List<User> findUsers(@Param("role") String role);
}
