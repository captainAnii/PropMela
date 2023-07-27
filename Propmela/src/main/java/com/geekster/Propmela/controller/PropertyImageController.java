package com.geekster.Propmela.controller;

import com.geekster.Propmela.model.PropertyImage;
import com.geekster.Propmela.service.PropertyImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/property-images")
public class PropertyImageController {
    private final PropertyImageService propertyImageService;

    // Constructor injection
    public PropertyImageController(PropertyImageService propertyImageService) {
        this.propertyImageService = propertyImageService;
    }

    @PostMapping
    public ResponseEntity<PropertyImage> createPropertyImage(@RequestBody PropertyImage propertyImage) {
        PropertyImage createdImage = propertyImageService.createPropertyImage(propertyImage);
        return new ResponseEntity<>(createdImage, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyImage> getPropertyImageById(@PathVariable Long id) {
        PropertyImage image = propertyImageService.getPropertyImageById(id);
        if (image != null) {
            return new ResponseEntity<>(image, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyImage> updatePropertyImage(@PathVariable Long id, @RequestBody PropertyImage propertyImage) {
        PropertyImage updatedImage = propertyImageService.updatePropertyImage(id, propertyImage);
        if (updatedImage != null) {
            return new ResponseEntity<>(updatedImage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropertyImage(@PathVariable Long id) {
        boolean deleted = propertyImageService.deletePropertyImage(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Implement other property image-related CRUD operations
}