package com.yinya.bellidoserranadrianapmdm03;

public class CapturedPokemonData {
    private String image;
    private String name;
    private String type;
    private float weight;
    private int id;
    private float height;

    public CapturedPokemonData(String image, String name, String type, float weight, int id, float height) {
        this.image = image;
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.id = id;
        this.height = height;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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

    public void setType(String type) {
        this.type = type;
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

