package com.application.app.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepositoryInterface extends JpaRepository<Recipe, Long>, JpaSpecificationExecutor<Recipe> {
    Optional<Recipe> findById(Long id);

    Page<Recipe> findAll(Pageable pageable);
}
