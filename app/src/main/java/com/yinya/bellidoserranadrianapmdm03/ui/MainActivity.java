package com.yinya.bellidoserranadrianapmdm03.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yinya.bellidoserranadrianapmdm03.PokedexListFragment;
import com.yinya.bellidoserranadrianapmdm03.R;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.IPokemonApi;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.PokemonApiService;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models.PokemonListResponseItemDataClass;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models.PokemonResponseDataClass;
import com.yinya.bellidoserranadrianapmdm03.data.network.repository.NetworkRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public NetworkRepository networkRepository;

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
        initNetworkRepository();
        fetchPokemons();
    }

    private void initNetworkRepository() {
        networkRepository = NetworkRepository.getInstance();
    }

    private void fetchPokemons() {
        networkRepository.fetchPokemonsFromApi();
    }
}