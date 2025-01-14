package com.yinya.bellidoserranadrianapmdm03.data.network.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.IPokemonApi;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.PokemonApiService;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models.PokemonResponseDataClass;
import com.yinya.bellidoserranadrianapmdm03.data.network.repository.models.PokemonListItemApiModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkRepository {
    private static NetworkRepository instance;
    private static IPokemonApi apiService;
    private MutableLiveData<List<PokemonListItemApiModel>> _pokemons = new MutableLiveData(null);

    private NetworkRepository() {
        PokemonApiService service = PokemonApiService.getInstance();
        apiService = service.getRetrofitService();
    }

    public static NetworkRepository getInstance() {
        if (instance == null) {
            instance = new NetworkRepository();
        }
        return instance;
    }

    public void fetchPokemonsFromApi() {

        // Llamar al método fetchPokemon
        Call<PokemonResponseDataClass> call = apiService.fetchPokemonsList(); // ID del Pokémon

        // Ejecutar la llamada de forma asíncrona
        call.enqueue(new Callback<PokemonResponseDataClass>() {
            @Override
            public void onResponse(Call<PokemonResponseDataClass> call, Response<PokemonResponseDataClass> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PokemonResponseDataClass pokemonResponse = response.body();
                    List<PokemonListItemApiModel> pokemonList = pokemonResponse.asPokemonListApiModel();
                    _pokemons.setValue(pokemonList);
                    Log.d("POKEMON", "Fetch success");
                } else {
                    Log.e("POKEMON", "Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponseDataClass> call, Throwable t) {
                Log.e("POKEMON", "Error al obtener el Pokémon: " + t.getMessage());
            }
        });
    }

    public LiveData<List<PokemonListItemApiModel>> getPokemons() {
        return _pokemons;
    }
}
