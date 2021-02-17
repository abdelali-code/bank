package com.example.bankFinal.models;

public class Entreprise extends Compte{
    private String nom;

    public Entreprise(int compteId, String nom, String numero, double solde) {
        //int id, double solde, String numero)
        super(compteId, solde, numero);
        this.nom = nom;
    }
    public Entreprise(int compteId, String nom, double solde) {
        //int id, double solde, String numero)
        super(compteId, solde);
        this.nom = nom;
    }

    public Entreprise( String nom, String numero, double solde) {
        super(solde, numero);
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
