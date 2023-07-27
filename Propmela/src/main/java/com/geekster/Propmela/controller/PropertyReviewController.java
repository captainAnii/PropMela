package com.geekster.Propmela.controller;

import com.geekster.Propmela.model.PropertyReview;
import com.geekster.Propmela.service.PropertyReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/property-reviews")
public class PropertyReviewController {
    private final PropertyReviewService propertyReviewService;

    // Constructor injection
    public PropertyReviewController(PropertyReviewService propertyReviewService) {
        this.propertyReviewService = propertyReviewService;
    }

    @PostMapping
    public ResponseEntity<PropertyReview> createPropertyReview(@RequestBody PropertyReview review) {
        PropertyReview createdReview = propertyReviewService.createPropertyReview(review);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyReview> getPropertyReviewById(@PathVariable Long id) {
        PropertyReview review = propertyReviewService.getPropertyReviewById(id);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyReview> updatePropertyReview(@PathVariable Long id, @RequestBody PropertyReview review) {
        PropertyReview updatedReview = propertyReviewService.updatePropertyReview(id, review);
        if (updatedReview != null) {
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropertyReview(@PathVariable Long id) {
        boolean deleted = propertyReviewService.deletePropertyReview(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}