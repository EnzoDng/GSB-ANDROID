package com.example.gsb;

public class Echantillon {

    protected String code;

    protected String libelle;

    protected String quantiteStock;

    public Echantillon() {
    }

    public Echantillon(String code, String libelle, String qteStock){
        this.code = code;
        this.libelle = libelle;
        this.quantiteStock = qteStock;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(String quantiteStock) {
        this.quantiteStock = quantiteStock;
    }
}
