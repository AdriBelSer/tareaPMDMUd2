package com.yinya.bellidoserranadrianapmdm03.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yinya.bellidoserranadrianapmdm03.R;
import com.yinya.bellidoserranadrianapmdm03.data.network.repository.models.PokemonDetailApiModel;
import com.yinya.bellidoserranadrianapmdm03.databinding.FragmentPokemonDetailBinding;
import com.yinya.bellidoserranadrianapmdm03.ui.models.PokedexPokemonData;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetailFragment extends Fragment {

    FragmentPokemonDetailBinding binding;
    private ArrayList<PokedexPokemonData> pokemons;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPokemonDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        fillPokemon();
        return view;
    }

    private void fillPokemon() {
        binding.ivPokemonDetailPokemonImg.setImageResource(R.drawable.bulbasaur);
        binding.tvPokemonDetailName.setText("Bulbasaur");
        //binding.tvPokemonDetailDescription.setText("A strange seed was planted on its back at birth. The plant sprouts and grows with this POKéMON.");
        binding.tvPokemonDetailHeightAmount.setText("6.9");
        binding.tvPokemonDetailWeightAmount.setText("70");
        binding.chipPokemonDetailType1.setText("grass");
        binding.chipPokemonDetailType2.setText("poison");
    }

}