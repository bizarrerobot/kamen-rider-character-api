package com.example.kamen_rider_character_api.controller;

import com.example.kamen_rider_character_api.model.RiderCharacter;
import com.example.kamen_rider_character_api.service.RiderCharacterService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class CharacterPageController {

    private final RiderCharacterService service;

    public CharacterPageController(RiderCharacterService service) {
        this.service = service;
    }

    // ---------- Gallery and search ----------

    @GetMapping("/")
    public String gallery(@RequestParam(required = false) String name,
                          @RequestParam(required = false) String universe,
                          Model model) {
        model.addAttribute("characters", service.searchCharacters(name, universe));
        model.addAttribute("name", name == null ? "" : name);
        model.addAttribute("universe", universe == null ? "" : universe);
        return "index";
    }

    // ---------- Details ----------

    @GetMapping("/characters/{id}")
    public String details(@PathVariable Long id, Model model, HttpServletResponse response) {
        Optional<RiderCharacter> found = service.getCharacterById(id);
        if (found.isEmpty()) {
            return notFound(response, model);
        }
        model.addAttribute("character", found.get());
        return "details";
    }

    // ---------- About ----------

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    // ---------- Add ----------

    @GetMapping("/characters/new")
    public String newForm(Model model) {
        fillForm(model, new RiderCharacter(), false, "/characters", new HashMap<>());
        return "character-form";
    }

    @PostMapping("/characters")
    public String create(@Valid @ModelAttribute("character") RiderCharacter character,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            fillForm(model, character, false, "/characters", errorsToMap(result));
            return "character-form";
        }
        RiderCharacter saved = service.createCharacter(character);
        redirectAttributes.addFlashAttribute("flash", saved.getName() + " was added");
        return "redirect:/";
    }

    // ---------- Edit ----------

    @GetMapping("/characters/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, HttpServletResponse response) {
        Optional<RiderCharacter> found = service.getCharacterById(id);
        if (found.isEmpty()) {
            return notFound(response, model);
        }
        fillForm(model, found.get(), true, "/characters/" + id, new HashMap<>());
        return "character-form";
    }

    @PostMapping("/characters/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("character") RiderCharacter character,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes,
                         HttpServletResponse response) {
        if (service.getCharacterById(id).isEmpty()) {
            return notFound(response, model);
        }
        if (result.hasErrors()) {
            fillForm(model, character, true, "/characters/" + id, errorsToMap(result));
            return "character-form";
        }
        service.updateCharacter(id, character);
        redirectAttributes.addFlashAttribute("flash", character.getName() + " was updated");
        return "redirect:/characters/" + id;
    }

    // ---------- Delete ----------

    @PostMapping("/characters/{id}/delete")
    public String delete(@PathVariable Long id,
                         Model model,
                         RedirectAttributes redirectAttributes,
                         HttpServletResponse response) {
        Optional<RiderCharacter> found = service.getCharacterById(id);
        if (found.isEmpty()) {
            return notFound(response, model);
        }
        String name = found.get().getName();
        service.deleteCharacter(id);
        redirectAttributes.addFlashAttribute("flash", name + " was removed");
        return "redirect:/";
    }

    // ---------- Helpers ----------

    private String notFound(HttpServletResponse response, Model model) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        model.addAttribute("errorMessage", "That character could not be found.");
        return "error";
    }

    private void fillForm(Model model, RiderCharacter character, boolean editing,
                          String action, Map<String, String> errors) {
        model.addAttribute("character", character);
        model.addAttribute("editing", editing);
        model.addAttribute("formAction", action);
        model.addAttribute("formTitle", editing ? "Edit Character" : "New Character Form");
        model.addAttribute("errors", errors);
    }

    private Map<String, String> errorsToMap(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : result.getFieldErrors()) {
            errors.putIfAbsent(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }
}