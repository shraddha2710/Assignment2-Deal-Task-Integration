package com.Assignment_2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Assignment_2.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
