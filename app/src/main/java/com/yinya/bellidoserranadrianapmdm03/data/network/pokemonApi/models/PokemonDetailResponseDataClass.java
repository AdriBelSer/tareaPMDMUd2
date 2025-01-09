package com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models;

import java.util.List;

public class PokemonDetailResponseDataClass {
    private int id;
    private String name;
    private int weight;
    private int height;
    private PokemonSpritesResponseDataClass sprites;
    private List<PokemonTypeResponseDataClass> types;

    public PokemonDetailResponseDataClass(int id, String name, int weight, int height, PokemonSpritesResponseDataClass sprites, List<PokemonTypeResponseDataClass> types) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.sprites = sprites;
        this.types = types;
    }
}
