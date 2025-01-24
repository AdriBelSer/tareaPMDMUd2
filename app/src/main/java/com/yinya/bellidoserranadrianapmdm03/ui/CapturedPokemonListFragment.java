package com.yinya.bellidoserranadrianapmdm03.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yinya.bellidoserranadrianapmdm03.data.network.repository.models.PokemonDetailApiModel;
import com.yinya.bellidoserranadrianapmdm03.databinding.FragmentCapturedPokemonListBinding;
import com.yinya.bellidoserranadrianapmdm03.ui.adapters.CapturedPokemonListAdapter;
import com.yinya.bellidoserranadrianapmdm03.ui.models.CapturedPokemonData;
import com.yinya.bellidoserranadrianapmdm03.ui.models.PokedexPokemonData;

import java.util.ArrayList;
import java.util.List;


public class CapturedPokemonListFragment extends Fragment {

    private FragmentCapturedPokemonListBinding binding;
    private RecyclerView pokemonsRv;
    private CapturedPokemonListAdapter adapter;
    private ArrayList<CapturedPokemonData> capturedPokemons = new ArrayList<>();
    private ArrayList<PokemonDetailApiModel> repositoryCapturedPokemons = new ArrayList<>();
    LiveData<List<PokemonDetailApiModel>> capturedPokemonsLiveData;
    private Observer<List<PokemonDetailApiModel>> observer = repositoryCapturedPokemons -> {
        if (repositoryCapturedPokemons != null) {
            for (PokemonDetailApiModel p : repositoryCapturedPokemons) {
                this.capturedPokemons.add(new CapturedPokemonData(
                        p.getId(),
                        p.getName(),
                        p.getFrontDefault(),
                        p.getType1(),
                        p.getType2(),
                        p.getWeight(),
                        p.getHeight()
                ));
            }
            fillRecyclerView(this.capturedPokemons);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCapturedPokemonListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        MainActivity activity = (MainActivity) requireActivity();
        requireNetworkRepository(activity);
        initRecyclerView();
        observeCapturedPokemonsLiveData(activity);

        return view;
    }

    private void initRecyclerView() {
        pokemonsRv = binding.rvCapturedPokemonList;

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        pokemonsRv.setLayoutManager(layoutManager);
        adapter = initializeAdapter(capturedPokemons);
        pokemonsRv.setAdapter(adapter);
    }

    private void requireNetworkRepository(MainActivity activity) {
        capturedPokemonsLiveData = activity.networkRepository.getCapturedPokemonsLiveData();;
    }

    private void fillRecyclerView(ArrayList<CapturedPokemonData> capturedPokemons) {
        adapter = initializeAdapter(capturedPokemons);
        pokemonsRv.setAdapter(adapter);
    }

    private CapturedPokemonListAdapter initializeAdapter(ArrayList<CapturedPokemonData> pokemonsList) {
        adapter = new CapturedPokemonListAdapter(pokemonsList, requireContext());
        return adapter;
    }

    private void observeCapturedPokemonsLiveData(MainActivity activity) {
        capturedPokemonsLiveData.observe(activity, observer);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (observer != null) {
            capturedPokemonsLiveData.removeObserver(observer);
        }
    }
}