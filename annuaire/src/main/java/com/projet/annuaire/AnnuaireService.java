package com.projet.annuaire;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AnnuaireService {
    
    // Point 1 : Thread-safe et recherche O(1)
    private final Map<String, Association> associations = new ConcurrentHashMap<>();

    public AnnuaireService() {
        // Point 5 : UUID partout
        String id1 = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();
        associations.put(id1, new Association(id1, "Croix-Rouge", "Hébergement et Repas", "Paris"));
        associations.put(id2, new Association(id2, "GISTI", "Aide Juridique", "Paris"));
    }

    public List<Association> getToutesLesAssociations() {
        return new ArrayList<>(associations.values());
    }

    // Point 3 : Méthode centralisée pour éviter la duplication
    public Association getAssociationParId(String id) {
        Association asso = associations.get(id);
        if (asso == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Association introuvable");
        }
        return asso;
    }

    public Association ajouterAssociation(Association nouvelleAsso) {
        String id = UUID.randomUUID().toString();
        nouvelleAsso.setId(id);
        associations.put(id, nouvelleAsso);
        return nouvelleAsso;
    }

    public Association modifierAssociation(String id, Association details) {
        // Réutilisation de la recherche par ID
        Association existante = getAssociationParId(id);
        
        existante.setNom(details.getNom());
        existante.setDomaine(details.getDomaine());
        existante.setAdresse(details.getAdresse());
        
        return existante;
    }

    public void supprimerAssociation(String id) {
        if (associations.remove(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Association introuvable");
        }
    }
}