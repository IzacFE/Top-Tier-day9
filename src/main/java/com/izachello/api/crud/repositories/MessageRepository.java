package com.izachello.api.crud.repositories;

import com.izachello.api.crud.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
