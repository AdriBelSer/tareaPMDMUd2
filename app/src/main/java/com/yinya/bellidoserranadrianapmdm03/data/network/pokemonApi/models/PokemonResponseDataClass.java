package com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models;

import com.yinya.bellidoserranadrianapmdm03.data.network.repository.models.PokemonListItemApiModel;

import java.util.ArrayList;
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

    public List<PokemonListResponseItemDataClass> getPokemonList() {
        return results;
    }

    public List<PokemonListItemApiModel> asPokemonListApiModel() {
        List<PokemonListItemApiModel> pokemons = new ArrayList();
        results.forEach(i -> pokemons.add(new PokemonListItemApiModel(i.getId(), i.getName())));
        return pokemons;
    }

    public static class PokemonListResponseItemDataClass {
        private String name;
        private String url;

        public PokemonListResponseItemDataClass(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            String[] parts = url.split("/");
            return Integer.parseInt(parts[parts.length - 1]);
        }
    }


}
