package com.geekster.Propmela.service;

import com.geekster.Propmela.model.PropertyReview;
import com.geekster.Propmela.repository.PropertyReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyReviewService {
    private final PropertyReviewRepository propertyReviewRepository;

    // Constructor injection
    public PropertyReviewService(PropertyReviewRepository propertyReviewRepository) {
        this.propertyReviewRepository = propertyReviewRepository;
    }

    public PropertyReview createPropertyReview(PropertyReview review) {
        // Implement property review creation logic with necessary validations
        return propertyReviewRepository.save(review);
    }

    public PropertyReview getPropertyReviewById(Long id) {
        // Implement logic to retrieve a property review by its ID
        return propertyReviewRepository.findById(id).orElse(null);
    }

    public PropertyReview updatePropertyReview(Long id, PropertyReview review) {
        // Implement logic to update property review data by ID
        PropertyReview existingReview = propertyReviewRepository.findById(id).orElse(null);
        if (existingReview != null) {
            // Perform necessary updates to the existingReview object based on the provided review data
            // For example: existingReview.setRating(review.getRating());
            return propertyReviewRepository.save(existingReview);
        }
        return null;
    }

    public boolean deletePropertyReview(Long id) {
        // Implement logic to delete a property review by ID
        PropertyReview review = propertyReviewRepository.findById(id).orElse(null);
        if (review != null) {
            propertyReviewRepository.delete(review);
            return true;
        }
        return false;
    }

    // Implement other property review-related service methods
}