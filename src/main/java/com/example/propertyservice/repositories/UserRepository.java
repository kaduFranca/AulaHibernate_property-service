package com.example.propertyservice.repositories;

import com.example.propertyservice.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    boolean existsByCpf(String cpf);

    public boolean existsByEmail(String email);
}
