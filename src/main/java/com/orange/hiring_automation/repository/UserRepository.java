package com.orange.hiring_automation.repository;

import com.orange.hiring_automation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
