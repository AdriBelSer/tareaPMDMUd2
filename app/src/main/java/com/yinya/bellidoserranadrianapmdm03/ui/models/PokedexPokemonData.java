package com.yinya.bellidoserranadrianapmdm03.ui.models;

public class PokedexPokemonData {
    int id;
    boolean isCaptured;
    String name;

    public PokedexPokemonData(int id, boolean isCaptured, String name) {
        this.id = id;
        this.isCaptured = isCaptured;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public boolean isCaptured() {
        return isCaptured;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCaptureState(boolean isCaptured) {
        this.isCaptured = isCaptured;
    }

    public void setName(String name) {
        this.name = name;
    }
}
