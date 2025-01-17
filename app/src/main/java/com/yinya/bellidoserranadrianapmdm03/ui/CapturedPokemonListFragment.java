package com.yinya.bellidoserranadrianapmdm03.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yinya.bellidoserranadrianapmdm03.R;


public class CapturedPokemonListFragment extends Fragment {
    private RecyclerView pokemonsRv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_captured_pokemon_list, container, false);
    }
}