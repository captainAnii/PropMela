package com.geekster.Propmela.repository;

import com.geekster.Propmela.model.PropertyImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyImageRepository extends JpaRepository<PropertyImage,Long> {
}
