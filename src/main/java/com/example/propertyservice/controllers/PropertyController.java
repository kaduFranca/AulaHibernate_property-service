package com.example.propertyservice.controllers;

import com.example.propertyservice.DTOs.PropertyDto;
import com.example.propertyservice.models.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.propertyservice.services.PropertyService;

import javax.validation.Valid;

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

}
