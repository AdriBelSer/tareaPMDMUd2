package com.yinya.bellidoserranadrianapmdm03.data.network.repository.models;

import com.yinya.bellidoserranadrianapmdm03.ui.models.PokedexPokemonData;

import java.util.ArrayList;
import java.util.List;

public class PokemonListApiModel {
    private List<PokemonListItemApiModel> pokemons;

    public static ArrayList<PokedexPokemonData> asPokemonListApiModel(List<PokemonListItemApiModel> pokemonsListApiModel) {
        ArrayList<PokedexPokemonData> pokemons = new ArrayList();
        pokemonsListApiModel.forEach(i -> pokemons.add(new PokedexPokemonData(i.getId(), false, i.getName())));
        return pokemons;
    }
}
