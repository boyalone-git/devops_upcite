package com.projet.annuaire;

public class Association {
    private String id;
    private String nom;
    private String domaine;
    private String adresse;

    public Association(String id, String nom, String domaine, String adresse) {
        this.id = id;
        this.nom = nom;
        this.domaine = domaine;
        this.adresse = adresse;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDomaine() { return domaine; }
    public void setDomaine(String domaine) { this.domaine = domaine; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
}