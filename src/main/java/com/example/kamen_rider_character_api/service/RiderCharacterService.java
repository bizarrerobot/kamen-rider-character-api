package com.example.kamen_rider_character_api.service;

import com.example.kamen_rider_character_api.model.RiderCharacter;
import com.example.kamen_rider_character_api.repository.RiderCharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RiderCharacterService {

    private final RiderCharacterRepository repository;

    public RiderCharacterService(RiderCharacterRepository repository) {
        this.repository = repository;
    }

    public List<RiderCharacter> getAllCharacters() {
        return repository.findAll();
    }

    public Optional<RiderCharacter> getCharacterById(Long id) {
        return repository.findById(id);
    }

    public RiderCharacter createCharacter(RiderCharacter character) {
        return repository.save(character);
    }

    public Optional<RiderCharacter> updateCharacter(
            Long id, RiderCharacter updatedCharacter) {

        return repository.findById(id).map(character -> {
            character.setName(updatedCharacter.getName());
            character.setDescription(updatedCharacter.getDescription());
            character.setUniverse(updatedCharacter.getUniverse());
            character.setRiderType(updatedCharacter.getRiderType());

            return repository.save(character);
        });
    }

    public boolean deleteCharacter(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }

    public List<RiderCharacter> getCharactersByUniverse(String universe) {
        return repository.findByUniverseIgnoreCase(universe);
    }

    public List<RiderCharacter> searchCharactersByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}