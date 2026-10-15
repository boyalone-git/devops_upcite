package com.projet.annuaire;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/associations")
public class AnnuaireController {

    private List<Association> associations = new ArrayList<>();

    public AnnuaireController() {
        associations.add(new Association("1", "Croix-Rouge", "Hébergement et Repas", "Paris"));
        associations.add(new Association("2", "GISTI", "Aide Juridique", "Paris"));
    }

    @GetMapping
    public List<Association> getAssociations() {
        return associations;
    }
}