package com.yinya.bellidoserranadrianapmdm03.data.network.repository.models;

public class PokemonListItemApiModel {
    int id;
    String name;

    public PokemonListItemApiModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }
}
