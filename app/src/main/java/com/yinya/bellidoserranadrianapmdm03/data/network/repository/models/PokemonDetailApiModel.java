package com.yinya.bellidoserranadrianapmdm03.data.network.repository.models;

public class PokemonDetailApiModel {
    private int id;
    private String name;
    private float weight;
    private float height;
    private String type1;
    private String type2;
    private String frontDefault;
    private String officialArtwork;

    public PokemonDetailApiModel(int id, String name, float weight, float height, String type1, String type2, String frontDefault, String officialArtwork) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.type1 = type1;
        this.type2 = type2;
        this.frontDefault = frontDefault;
        this.officialArtwork = officialArtwork;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }

    public String getOfficialArtwork() {
        return officialArtwork;
    }

    public void setOfficialArtwork(String officialArtwork) {
        this.officialArtwork = officialArtwork;
    }
}
