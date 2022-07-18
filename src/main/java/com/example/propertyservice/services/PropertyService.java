package com.example.propertyservice.services;

import com.example.propertyservice.models.UserModel;
import com.example.propertyservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//Controller aciona o Service e o Service aciona o Repository

@Service
public class PropertyService { //Controller acionar o service e o service aciona o Repository

    final UserRepository userRepository;

    public PropertyService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public boolean existsByCpf(String cpf) {
        return userRepository.existsByCpf(cpf);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
