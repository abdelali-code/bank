package com.example.bankFinal.models;

public class Compte {
    private int id;
    private double solde;
    private String numero;

    public Compte(int id, double solde, String numero) {
        this.id = id;
        this.solde = solde;
        this.numero = numero;
    }

    public Compte(double solde, String numero) {
        this.solde = solde;
        this.numero = numero;
    }

    public Compte(int id, double solde) {
        this.solde = solde;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
