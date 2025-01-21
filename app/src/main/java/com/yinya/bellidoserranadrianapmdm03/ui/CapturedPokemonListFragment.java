package com.yinya.bellidoserranadrianapmdm03.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yinya.bellidoserranadrianapmdm03.databinding.FragmentCapturedPokemonListBinding;
import com.yinya.bellidoserranadrianapmdm03.ui.adapters.CapturedPokemonListAdapter;
import com.yinya.bellidoserranadrianapmdm03.ui.models.CapturedPokemonData;

import java.util.ArrayList;


public class CapturedPokemonListFragment extends Fragment {

    private FragmentCapturedPokemonListBinding binding;
    private RecyclerView pokemonsRv;
    private CapturedPokemonListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCapturedPokemonListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        initRecyclerView();

        return view;
    }

    private void initRecyclerView() {
        pokemonsRv = binding.rvCapturedPokemonList;

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        pokemonsRv.setLayoutManager(layoutManager);
        CapturedPokemonData pokemon = new CapturedPokemonData(
                1,
                "bulbasaur",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                "grass",
                "poison",
                6.9f,
                70f

        );
        ArrayList<CapturedPokemonData> capturedPokemons = new ArrayList<>();
        capturedPokemons.add(pokemon);
        adapter = initializeAdapter(capturedPokemons);
        pokemonsRv.setAdapter(adapter);
    }

    private void fillRecyclerView(ArrayList<CapturedPokemonData> capturedPokemons) {
        adapter = initializeAdapter(capturedPokemons);
        pokemonsRv.setAdapter(adapter);
    }

    private CapturedPokemonListAdapter initializeAdapter(ArrayList<CapturedPokemonData> pokemonsList) {
        adapter = new CapturedPokemonListAdapter(pokemonsList, requireContext());
        return adapter;
    }
}