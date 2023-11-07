package com.nextShop.auth.repository;

import com.nextShop.auth.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

}
