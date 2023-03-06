package com.woo.downbit.repository.user;

import com.woo.downbit.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
