package com.yinya.bellidoserranadrianapmdm03.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.yinya.bellidoserranadrianapmdm03.databinding.FragmentPokemonDetailBinding;
import com.yinya.bellidoserranadrianapmdm03.ui.models.PokedexPokemonData;

import java.util.ArrayList;

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
        setDefaultActionBar();
        Bundle arguments = getArguments();
        fillPokemon(arguments);
        return view;
    }

    /**
     * Sets the default action bar using a MaterialToolbar.
     * Configures the AppBarLayout to disable lift-on-scroll behavior.
     */
    private void setDefaultActionBar() {
        MaterialToolbar toolbar = binding.mtPokemonDetail;
        AppBarLayout appBarLayout = binding.appbarLayoutPokemonDetail;
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        activity.setSupportActionBar(toolbar);

        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false); // Sin texto
        }
        appBarLayout.setLiftOnScroll(false);
        toolbar.setNavigationOnClickListener(v -> {
            requireActivity().getOnBackPressedDispatcher().onBackPressed();
        });
    }

    private void fillPokemon(Bundle arguments) {
        String imageUrl = arguments.getString("imageUrl");
        Glide.with(requireContext()).load(imageUrl).into(binding.ivPokemonDetailPokemonImg);
        binding.tvPokemonDetailName.setText(arguments.getString("name"));
        binding.tvPokemonDetailHeightAmount.setText(arguments.getString("height"));
        binding.tvPokemonDetailWeightAmount.setText(arguments.getString("weight"));
        String type1 = arguments.getString("type1");
        String type2 = arguments.getString("type2");
        if (type1 != "" && type1 != null) {
            binding.chipPokemonDetailType1.setText(type1);
        } else {
            binding.chipPokemonDetailType1.setText("-");
        }
        if (type2 != "" && type2 != null) {
            binding.chipPokemonDetailType2.setText(type2);
        } else {
            binding.chipPokemonDetailType2.setVisibility(View.GONE);
        }
    }

}