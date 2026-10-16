package com.example.kamen_rider_character_api.controller;

import com.example.kamen_rider_character_api.model.RiderCharacter;
import com.example.kamen_rider_character_api.service.RiderCharacterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class RiderCharacterController {

    private final RiderCharacterService service;

    public RiderCharacterController(RiderCharacterService service) {
        this.service = service;
    }

    @GetMapping
    public List<RiderCharacter> getCharacters(
            @RequestParam(required = false) String universe,
            @RequestParam(required = false) String name) {

        if (universe != null && !universe.isBlank()) {
            return service.getCharactersByUniverse(universe);
        }

        if (name != null && !name.isBlank()) {
            return service.searchCharactersByName(name);
        }

        return service.getAllCharacters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiderCharacter> getCharacterById(
            @PathVariable Long id) {

        return service.getCharacterById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RiderCharacter> createCharacter(
            @Valid @RequestBody RiderCharacter character) {

        RiderCharacter savedCharacter = service.createCharacter(character);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RiderCharacter> updateCharacter(
            @PathVariable Long id,
            @Valid @RequestBody RiderCharacter updatedCharacter) {

        return service.updateCharacter(id, updatedCharacter)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {

        if (!service.deleteCharacter(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}