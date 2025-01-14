package com.yinya.bellidoserranadrianapmdm03.data.network.repository.models;

import com.yinya.bellidoserranadrianapmdm03.PokedexData;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models.PokemonListResponseItemDataClass;

import java.util.ArrayList;
import java.util.List;

public class PokemonListApiModel {
    private List<PokemonListItemApiModel> pokemons;

    public static ArrayList<PokedexData> asPokemonListApiModel(List<PokemonListItemApiModel> pokemonsListApiModel) {
        ArrayList<PokedexData> pokemons = new ArrayList();
        pokemonsListApiModel.forEach(i -> pokemons.add(new PokedexData(1, "", i.getName())));
        return pokemons;
    }
}
