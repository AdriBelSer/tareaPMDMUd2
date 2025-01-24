package com.yinya.bellidoserranadrianapmdm03.ui.models;

public class CapturedPokemonData {
    private int id;
    private String name;
    private String frontImage;
    private String detailImage;
    private String type1;
    private String type2;
    private float weight;
    private float height;

    public CapturedPokemonData(int id, String name, String frontImage, String detailImage, String type1, String type2, float weight, float height) {
        this.id = id;
        this.frontImage = frontImage;
        this.detailImage = detailImage;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.weight = weight;
        this.height = height;
    }

    public String getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypes() {
        if (type1.isEmpty() && type2.isEmpty()) {
            return "";
        }
        return type1 + (type1.isEmpty() ? "" : ", ") + type2;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setTypes(String type1, String type2) {
        this.type1 = (type1 != null) ? type1 : "";
        this.type2 = (type2 != null) ? type2 : "";
    }

    public String getDetailImage() {
        return detailImage;
    }

    public void setDetailImage(String detailImage) {
        this.detailImage = detailImage;
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
}

