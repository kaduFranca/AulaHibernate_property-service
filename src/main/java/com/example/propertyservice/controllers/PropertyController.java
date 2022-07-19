package com.example.propertyservice.controllers;

import com.example.propertyservice.DTOs.PropertyDto;
import com.example.propertyservice.models.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.propertyservice.services.PropertyService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/property-service") //URI a nível de classe
public class PropertyController {

    final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid PropertyDto propertyDto){
        if(propertyService.existsByCpf(propertyDto.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Aviso: Esse CPF ja está em uso.");
        }
        if(propertyService.existsByEmail(propertyDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Aviso: Esse email ja está em uso.");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(propertyDto, userModel); //dto convertido para model
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.save(userModel));
    }
    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id){
        Optional<UserModel> userModelOptional = propertyService.findById(id);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id) {
        Optional<UserModel> userModelOptional = propertyService.findById(id);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        propertyService.delete(userModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso")
    }
}
