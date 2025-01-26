package com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi;

import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models.PokemonDetailResponseDataClass;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models.PokemonResponseDataClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IPokemonApi {
    @GET("pokemon/{id}")
    Call<PokemonDetailResponseDataClass> fetchPokemon(@Path("id") String id);

    @GET("pokemon?limit=150")
    Call<PokemonResponseDataClass> fetchPokemonsList();
}
