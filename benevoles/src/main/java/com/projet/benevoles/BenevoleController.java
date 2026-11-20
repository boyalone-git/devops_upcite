package com.projet.benevoles;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/benevoles")
public class BenevoleController {

    private final BenevoleRepository repository;

    public BenevoleController(BenevoleRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Benevole> getBenevoles() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Benevole ajouterBenevole(@Valid @RequestBody Benevole benevole) {
        return repository.save(benevole);
    }
}