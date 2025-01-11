package com.yinya.bellidoserranadrianapmdm03;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yinya.bellidoserranadrianapmdm03.databinding.FragmentPokedexListBinding;

import java.util.ArrayList;

public class PokedexListFragment extends Fragment {


    private FragmentPokedexListBinding binding;
    private RecyclerView  pokemonsRv;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPokedexListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        pokemonsRv = binding.rvPokedexList;
        PokedexData pokemon1 = new PokedexData(1, "", "Pikachu");
        PokedexData pokemon2 = new PokedexData(2, "", "Charmander");
        ArrayList<PokedexData> pokemonsList = new ArrayList<>();
        pokemonsList.add(pokemon1);
        pokemonsList.add(pokemon2);
        PokedexListAdapter adapter = initializeAdapter (pokemonsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        pokemonsRv.setLayoutManager(layoutManager);
        pokemonsRv.setAdapter(adapter);
        return view;
    }

    private PokedexListAdapter initializeAdapter(ArrayList<PokedexData> pokemonsList) {
        PokedexListAdapter adapter = new PokedexListAdapter(pokemonsList, requireContext());
        return adapter;
    }
}