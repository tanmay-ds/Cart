package com.cart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{

}
