package com.application.app.fridge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FridgeRepositoryInterface extends JpaRepository<Fridge, Long> {
}
