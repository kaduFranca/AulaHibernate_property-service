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
        var userModel = new UserModel();
        BeanUtils.copyProperties(propertyDto, userModel); //dto convertido para model
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.save(userModel));
    }
    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return propertyService.findAll();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id){
        return propertyService.findById(id);
    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<Object> getOneUserByName(@PathVariable(value = "nome") String nome){
       return propertyService.findByNome(nome);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id) {
        return propertyService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id,
                                             @RequestBody @Valid PropertyDto propertyDto){
        return propertyService.update(id, propertyDto);
    }
}
