package controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.PropertyService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/property-service")
public class PropertyController {

    final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


}
