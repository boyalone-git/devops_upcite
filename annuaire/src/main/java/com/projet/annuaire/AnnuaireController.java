package com.projet.annuaire;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/associations")
public class AnnuaireController {

    private List<Association> associations = new ArrayList<>();

    public AnnuaireController() {
        associations.add(new Association("1", "Croix-Rouge", "Hébergement et Repas", "Paris"));
        associations.add(new Association("2", "GISTI", "Aide Juridique", "Paris"));
    }

    // Route GET : Lister les associations
    @GetMapping
    public List<Association> getAssociations() {
        return associations;
    }

    // Route POST : Ajouter une nouvelle association avec un ID unique généré par le serveur
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Association ajouterAssociation(@RequestBody Association nouvelleAssociation) {
        // On génère un ID unique automatiquement (ex: "550e8400-e29b-41d4-a716-446655440000")
        nouvelleAssociation.setId(UUID.randomUUID().toString());
        
        associations.add(nouvelleAssociation);
        return nouvelleAssociation;
    }
}