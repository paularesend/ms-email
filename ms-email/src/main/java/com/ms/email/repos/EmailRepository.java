package com.ms.email.repos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.email.models.Email;

public interface EmailRepository extends JpaRepository<Email, UUID>{

}
