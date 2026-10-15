package com.projet.annuaire;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/associations")
public class AnnuaireController {

    // Point 4 : Injection du Service
    private final AnnuaireService annuaireService;

    public AnnuaireController(AnnuaireService annuaireService) {
        this.annuaireService = annuaireService;
    }

    @GetMapping
    public List<Association> getAssociations() {
        return annuaireService.getToutesLesAssociations();
    }

    @GetMapping("/{id}")
    public Association getAssociationById(@PathVariable String id) {
        return annuaireService.getAssociationParId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Association ajouterAssociation(@Valid @RequestBody Association nouvelleAssociation) {
        return annuaireService.ajouterAssociation(nouvelleAssociation);
    }

    @PutMapping("/{id}")
    public Association modifierAssociation(@PathVariable String id, @Valid @RequestBody Association detailsModifies) {
        return annuaireService.modifierAssociation(id, detailsModifies);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimerAssociation(@PathVariable String id) {
        annuaireService.supprimerAssociation(id);
    }
}