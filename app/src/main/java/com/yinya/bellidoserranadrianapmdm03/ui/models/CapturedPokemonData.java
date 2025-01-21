package com.yinya.bellidoserranadrianapmdm03.ui.models;

public class CapturedPokemonData {
    private int id;
    private String name;
    private String image;
    private String type1;
    private String type2;
    private float weight;
    private float height;

    public CapturedPokemonData(int id, String name, String image, String type1, String type2, float weight, float height) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.weight = weight;
        this.height = height;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
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

    public int getId() {
        return id;
    }

    public float getHeight() {
        return height;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypes(String type1, String type2) {
        this.type1 = (type1 != null) ? type1 : "";
        this.type2 = (type2 != null) ? type2 : "";
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}

