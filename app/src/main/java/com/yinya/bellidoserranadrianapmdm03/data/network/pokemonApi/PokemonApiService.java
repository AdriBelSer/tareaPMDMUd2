package com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models.PokemonDetailResponseDataClass;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models.PokemonResponseDataClass;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.Call;

interface IPokemonApi {
    @GET("pokemon/{id}")
    Call<PokemonDetailResponseDataClass> fetchPokemon(@Path("id") int id);

    @GET("pokemon")
    Call<PokemonResponseDataClass> fetchPokemonsList();
}

public class PokemonApiService {

    private static PokemonApiService instance;
    private static IPokemonApi retrofit;

    private PokemonApiService() {
        getRetrofitService();
    }

    public static PokemonApiService getInstance() {
        if (instance == null) {
            instance = new PokemonApiService();
        }
        return instance;
    }

    public static IPokemonApi getRetrofitService() {
        if (retrofit == null) {
            Gson gson = createGson();
            Retrofit _retrofit = new Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            retrofit = _retrofit.create(IPokemonApi.class);
        }
        return retrofit;
    }

    private static Gson createGson() {
        return new GsonBuilder()
                .create();
    }
}
