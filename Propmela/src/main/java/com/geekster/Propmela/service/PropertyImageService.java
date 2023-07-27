package com.geekster.Propmela.service;

import com.geekster.Propmela.model.PropertyImage;
import com.geekster.Propmela.repository.PropertyImageRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyImageService {
    private final PropertyImageRepository propertyImageRepository;

    // Constructor injection
    public PropertyImageService(PropertyImageRepository propertyImageRepository) {
        this.propertyImageRepository = propertyImageRepository;
    }

    public PropertyImage createPropertyImage(PropertyImage propertyImage) {
        // Implement property image creation logic with necessary validations
        return propertyImageRepository.save(propertyImage);
    }

    public PropertyImage getPropertyImageById(Long id) {
        // Implement logic to retrieve a property image by its ID
        return propertyImageRepository.findById(id).orElse(null);
    }

    public PropertyImage updatePropertyImage(Long id, PropertyImage propertyImage) {
        // Implement logic to update property image data by ID
        PropertyImage existingImage = propertyImageRepository.findById(id).orElse(null);
        if (existingImage != null) {
            // Perform necessary updates to the existingImage object based on the provided propertyImage data
            // For example: existingImage.setUrl(propertyImage.getUrl());
            return propertyImageRepository.save(existingImage);
        }
        return null;
    }

    public boolean deletePropertyImage(Long id) {
        // Implement logic to delete a property image by ID
        PropertyImage propertyImage = propertyImageRepository.findById(id).orElse(null);
        if (propertyImage != null) {
            propertyImageRepository.delete(propertyImage);
            return true;
        }
        return false;
    }

    // Implement other property image-related service methods
}