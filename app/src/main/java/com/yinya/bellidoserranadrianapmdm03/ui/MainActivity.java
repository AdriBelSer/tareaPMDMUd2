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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public NetworkRepository networkRepository;

    ActivityMainBinding binding;

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
        networkRepository.fetchOnePokemonFromApi(id);
        networkRepository.getCapturedPokemonsLiveData().observe(this, capturedPokemons -> {
            if (capturedPokemons != null) {
                Map<String, List<PokemonDetailApiModel>> pokemons = new HashMap<>();
                pokemons.put("pokemons", capturedPokemons);
                networkRepository.addPokemonsToUser(pokemons);
            }
        });
    }



    public void getCapturedPokemons() {

    }

    public void showPokemonDetail(int id, View view) {
        Bundle bundle = new Bundle();

        Navigation.findNavController(view).navigate(R.id.pokemonDetailFragment, bundle);
    }
}