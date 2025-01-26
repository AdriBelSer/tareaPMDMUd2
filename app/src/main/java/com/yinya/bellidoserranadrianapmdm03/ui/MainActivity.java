package com.yinya.bellidoserranadrianapmdm03.ui;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yinya.bellidoserranadrianapmdm03.R;
import com.yinya.bellidoserranadrianapmdm03.data.network.repository.NetworkRepository;
import com.yinya.bellidoserranadrianapmdm03.data.network.repository.models.PokemonDetailApiModel;
import com.yinya.bellidoserranadrianapmdm03.databinding.ActivityMainBinding;
import com.yinya.bellidoserranadrianapmdm03.ui.models.CapturedPokemonData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity {
    public NetworkRepository networkRepository;
    ActivityMainBinding binding;
    private List<PokemonDetailApiModel> capturedPokemons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        setInsets();
        setBottomNavigation();
        initNetworkRepository();
        fetchPokemons();
        getCapturedPokemons();
        startCapturedPokemonsObservation();
    }

    private void setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setBottomNavigation() {
        BottomNavigationView navView = binding.bottomNavigation;
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, navController);
        //Para colorear los iconos de menú
        navView.setItemIconTintList(null);
    }

    private void initNetworkRepository() {
        networkRepository = NetworkRepository.getInstance();
    }

    private void fetchPokemons() {
        networkRepository.fetchPokemonsFromApi();
    }

    public void fetchOnePokemon(int id) {
        boolean found = false;
        if (this.capturedPokemons != null) {
            found = capturedPokemons.stream().anyMatch(pokemon -> pokemon.getId() == id);
        }
        if (!found) {
            networkRepository.fetchOnePokemonFromApi(id);
        }
    }

    private void startCapturedPokemonsObservation() {
        networkRepository.getCapturedPokemonsLiveData().observe(this, capturedPokemons -> {
            if (capturedPokemons != null) {
                this.capturedPokemons = capturedPokemons;
                Map<String, List<PokemonDetailApiModel>> pokemons = new HashMap<>();
                pokemons.put("pokemons", capturedPokemons);
                networkRepository.setPokemonsToUser(pokemons);
            }
        });
    }

    public void getCapturedPokemons() {

    }

    public void showPokemonDetail(CapturedPokemonData currentPokemon, View view) {
        Bundle bundle = new Bundle();
        bundle.putString("imageUrl", currentPokemon.getDetailImage());
        bundle.putString("name", currentPokemon.getName());
        bundle.putString("weight", String.format("%.0f", currentPokemon.getWeight()));
        bundle.putString("height", String.format("%.1f", currentPokemon.getHeight()));
        bundle.putString("type1", currentPokemon.getType1());
        bundle.putString("type2", currentPokemon.getType2());
        Navigation.findNavController(view).navigate(R.id.action_capturedPokemonListFragment_to_pokemonDetailFragment, bundle);
    }

    public void deleteCapturedPokemon(int id) {
        networkRepository.deleteCapturedPokemon(id);
    }
}