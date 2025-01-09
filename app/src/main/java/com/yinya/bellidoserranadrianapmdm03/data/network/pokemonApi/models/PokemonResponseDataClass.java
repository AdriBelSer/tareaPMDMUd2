package com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models;

import java.util.List;

public class PokemonResponseDataClass {
    private int count;
    private String next;
    private String previous;
    private List<PokemonListResponseItemDataClass> results;

    public PokemonResponseDataClass(int count, String next, String previous, List<PokemonListResponseItemDataClass> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }
}
