package controllers;

import DTOs.PropertyDto;
import models.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.PropertyService;

import javax.validation.Valid;
import java.time.LocalDate;

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

}
