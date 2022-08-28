package com.izachello.api.crud.repositories;

import com.izachello.api.crud.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
