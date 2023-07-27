package com.geekster.Propmela.repository;

import com.geekster.Propmela.model.PropertyReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyReviewRepository extends JpaRepository<PropertyReview,Long> {
}
