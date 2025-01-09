package com.yinya.bellidoserranadrianapmdm03.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yinya.bellidoserranadrianapmdm03.R;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.IPokemonApi;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.PokemonApiService;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models.PokemonListResponseItemDataClass;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models.PokemonResponseDataClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<PokemonListResponseItemDataClass> pokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fetchPokemons();
    }

    private void fetchPokemons() {
        // Obtener la instancia del servicio
        PokemonApiService service = PokemonApiService.getInstance();
        IPokemonApi api = service.getRetrofitService();

        // Llamar al método fetchPokemon
        Call<PokemonResponseDataClass> call = api.fetchPokemonsList(); // ID del Pokémon

        // Ejecutar la llamada de forma asíncrona
        call.enqueue(new Callback<PokemonResponseDataClass>() {
            @Override
            public void onResponse(Call<PokemonResponseDataClass> call, Response<PokemonResponseDataClass> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PokemonResponseDataClass pokemon = response.body();
                    pokemonList = pokemon.getPokemonList();
                    System.out.println(pokemonList);
                } else {
                    System.out.println("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponseDataClass> call, Throwable t) {
                System.out.println("Error al obtener el Pokémon: " + t.getMessage());
            }
        });
    }
}