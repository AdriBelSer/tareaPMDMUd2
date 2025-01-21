package com.yinya.bellidoserranadrianapmdm03.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.yinya.bellidoserranadrianapmdm03.R;
import com.yinya.bellidoserranadrianapmdm03.ui.MainActivity;
import com.yinya.bellidoserranadrianapmdm03.ui.models.PokedexPokemonData;
import com.yinya.bellidoserranadrianapmdm03.databinding.CardListPokedexItemBinding;

import java.util.ArrayList;

public class PokedexListAdapter extends RecyclerView.Adapter<PokedexListAdapter.PokedexPokemonViewHolder> {

    private ArrayList<PokedexPokemonData> pokemons;
    private final Context context;

    public PokedexListAdapter(ArrayList<PokedexPokemonData> pokemons, Context context) {
        this.pokemons = pokemons;
        this.context = context;
    }

    @NonNull
    @Override
    public PokedexPokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardListPokedexItemBinding binding = CardListPokedexItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PokedexPokemonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PokedexPokemonViewHolder holder, int position) {
        PokedexPokemonData currentPokemon = pokemons.get(position);
        holder.pokemonName.setText(currentPokemon.getName());
        int pokeballRes = R.drawable.pokeball_off;
        if (currentPokemon.isCaptured()) {
            pokeballRes = R.drawable.pokeball_on;
        }
        holder.pokeballIcon.setImageResource(pokeballRes);
        holder.pokemonCard.setOnClickListener(v -> {
            ((MainActivity) context).fetchOnePokemon(currentPokemon.getId());
        });
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }


    public class PokedexPokemonViewHolder extends RecyclerView.ViewHolder {
        private ImageView pokeballIcon;
        private TextView pokemonName;
        private MaterialCardView pokemonCard;

        public PokedexPokemonViewHolder(@NonNull CardListPokedexItemBinding binding) {
            super(binding.getRoot());
            pokeballIcon = binding.ivPokeballItemList;
            pokemonName = binding.tvPokedexItemListName;
            pokemonCard = binding.cvPokedexList;
        }
    }
}
