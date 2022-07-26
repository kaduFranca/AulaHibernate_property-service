package com.example.propertyservice.services;

import com.example.propertyservice.models.UserModel;
import com.example.propertyservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hibernate.criterion.Projections.count;

//Controller aciona o Service e o Service aciona o Repository

@Service
public class PropertyService { //Controller aciona o service e o service aciona o Repository

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
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }
    public Optional<UserModel> findById(UUID id) {
        return userRepository.findById(id);
    }
    @Transactional
    public void delete(UserModel userModel) {
        userRepository.delete(userModel);
    }
}
