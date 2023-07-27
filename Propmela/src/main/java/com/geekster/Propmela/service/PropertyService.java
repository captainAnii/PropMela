package com.geekster.Propmela.service;

import com.geekster.Propmela.model.Property;
import com.geekster.Propmela.repository.PropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;

    // Constructor injection
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property createProperty(Property property) {
        // Implement property creation logic with necessary validations
        return propertyRepository.save(property);
    }

    public Property getPropertyById(Long id) {
        // Implement logic to retrieve a property by its ID
        return propertyRepository.findById(id).orElse(null);
    }

    public Property updateProperty(Long id, Property property) {
        // Implement logic to update property data by ID
        Property existingProperty = propertyRepository.findById(id).orElse(null);
        if (existingProperty != null) {
            // Perform necessary updates to the existingProperty object based on the provided property data
            // For example: existingProperty.setName(property.getName());
            return propertyRepository.save(existingProperty);
        }
        return null;
    }

    public boolean deleteProperty(Long id) {
        // Implement logic to delete a property by ID
        Property property = propertyRepository.findById(id).orElse(null);
        if (property != null) {
            propertyRepository.delete(property);
            return true;
        }
        return false;
    }

    // Implement other property-related service methods
}