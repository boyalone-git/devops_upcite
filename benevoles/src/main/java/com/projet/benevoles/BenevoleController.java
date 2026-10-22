package com.projet.benevoles;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/benevoles")
public class BenevoleController {

    @GetMapping
    public List<Benevole> getBenevoles() {
        return List.of(
            new Benevole("Alice", "Traduction"),
            new Benevole("Bob", "Accueil")
        );
    }
}