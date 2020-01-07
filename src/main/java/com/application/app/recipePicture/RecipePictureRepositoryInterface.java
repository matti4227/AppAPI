package com.application.app.recipePicture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipePictureRepositoryInterface extends JpaRepository<RecipePicture, Long> {
}
