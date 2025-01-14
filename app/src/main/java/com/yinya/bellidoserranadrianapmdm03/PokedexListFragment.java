package com.yinya.bellidoserranadrianapmdm03;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yinya.bellidoserranadrianapmdm03.data.network.repository.models.PokemonListItemApiModel;
import com.yinya.bellidoserranadrianapmdm03.data.network.repository.models.PokemonListApiModel;
import com.yinya.bellidoserranadrianapmdm03.databinding.FragmentPokedexListBinding;
import com.yinya.bellidoserranadrianapmdm03.ui.MainActivity;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPokedexListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        initRecyclerView();


        MainActivity activity = (MainActivity) requireActivity();

        LiveData<List<PokemonListItemApiModel>> pokemonsLiveData = activity.networkRepository.getPokemons();
        pokemonsLiveData.observe(requireActivity(), pokemons -> {
            if (pokemons != null) {
                this.pokemons = pokemons;
                ArrayList<PokedexData> pokedexData = PokemonListApiModel.asPokemonListApiModel(pokemons);
                fillRecyclerView(pokedexData);
            }
        });
        return view;
    }

    private void initRecyclerView() {
        pokemonsRv = binding.rvPokedexList;

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        pokemonsRv.setLayoutManager(layoutManager);

        adapter = initializeAdapter(new ArrayList());
        pokemonsRv.setAdapter(adapter);
    }

    private void fillRecyclerView(ArrayList<PokedexData> pokedexData) {
        adapter = initializeAdapter(pokedexData);
        pokemonsRv.setAdapter(adapter);
    }

    private PokedexListAdapter initializeAdapter(ArrayList<PokedexData> pokemonsList) {
        PokedexListAdapter adapter = new PokedexListAdapter(pokemonsList, requireContext());
        return adapter;
    }
}