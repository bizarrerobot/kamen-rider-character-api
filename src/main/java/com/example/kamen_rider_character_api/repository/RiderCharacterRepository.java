package com.example.kamen_rider_character_api.repository;

import com.example.kamen_rider_character_api.model.RiderCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RiderCharacterRepository
        extends JpaRepository<RiderCharacter, Long> {

    List<RiderCharacter> findByUniverseIgnoreCase(String universe);

    List<RiderCharacter> findByNameContainingIgnoreCase(String name);
}