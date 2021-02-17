package com.example.bankFinal.models;

public class Personne extends Compte{
    private String nom;
    private String prenom;

    public Personne(int id, double solde, String numero, String nom, String prenom) {
        super(id, solde, numero);
        this.nom = nom;
        this.prenom = prenom;
    }

    public Personne(int id, double solde, String nom, String prenom) {
        super(id, solde);
        this.nom = nom;
        this.prenom = prenom;
    }

    public Personne(double solde, String numero, String nom, String prenom) {
        super(solde, numero);
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
