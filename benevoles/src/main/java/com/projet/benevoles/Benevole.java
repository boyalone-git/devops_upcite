package com.projet.benevoles;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public class Benevole {
    private String id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le rôle est obligatoire (ex: Traduction, Accueil)")
    private String role;

    public Benevole() {}

    public Benevole(String nom, String role) {
        this.id = UUID.randomUUID().toString();
        this.nom = nom;
        this.role = role;
    }

    // Getters et Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}