package com.yinya.bellidoserranadrianapmdm03.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yinya.bellidoserranadrianapmdm03.ui.models.PokedexPokemonData;
import com.yinya.bellidoserranadrianapmdm03.data.network.repository.models.PokemonListItemApiModel;
import com.yinya.bellidoserranadrianapmdm03.data.network.repository.models.PokemonListApiModel;
import com.yinya.bellidoserranadrianapmdm03.databinding.FragmentPokedexListBinding;
import com.yinya.bellidoserranadrianapmdm03.ui.adapters.PokedexListAdapter;

import java.util.ArrayList;
import java.util.List;

public class PokedexListFragment extends Fragment {

    private List<PokemonListItemApiModel> pokemons;
    private FragmentPokedexListBinding binding;
    private RecyclerView pokemonsRv;
    private PokedexListAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPokedexListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        initRecyclerView();


        MainActivity activity = (MainActivity) requireActivity();

        LiveData<List<PokemonListItemApiModel>> pokemonsLiveData = activity.networkRepository.getAllPokemons();
        pokemonsLiveData.observe(requireActivity(), pokemons -> {
            if (pokemons != null) {
                this.pokemons = pokemons;
                ArrayList<PokedexPokemonData> pokedexPokemons = PokemonListApiModel.asPokemonListApiModel(pokemons);
                fillRecyclerView(pokedexPokemons);
            }
        });
        return view;
    }

    private void initRecyclerView() {
        pokemonsRv = binding.rvPokedexList;

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        pokemonsRv.setLayoutManager(layoutManager);

        adapter = initializeAdapter(new ArrayList<>());
        pokemonsRv.setAdapter(adapter);
    }

    private void fillRecyclerView(ArrayList<PokedexPokemonData> pokedexPokemons) {
        adapter = initializeAdapter(pokedexPokemons);
        pokemonsRv.setAdapter(adapter);
    }

    private PokedexListAdapter initializeAdapter(ArrayList<PokedexPokemonData> pokemonsList) {
        adapter = new PokedexListAdapter(pokemonsList, requireContext());
        return adapter;
    }
}