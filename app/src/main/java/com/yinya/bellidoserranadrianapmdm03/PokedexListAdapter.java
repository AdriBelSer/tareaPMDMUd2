package com.yinya.bellidoserranadrianapmdm03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.yinya.bellidoserranadrianapmdm03.databinding.CardListPokedexItemBinding;

import java.util.ArrayList;

public class PokedexListAdapter extends RecyclerView.Adapter <PokedexListAdapter.PokedexPokemonViewHolder> {

    private ArrayList<PokedexData> pokemons;
    private final Context context;

    public PokedexListAdapter(ArrayList<PokedexData> pokemons, Context context) {
        this.pokemons = pokemons;
        this.context = context;
    }

    public void setPokemonArrayList(ArrayList<PokedexData> pokemons) {
        this.pokemons = pokemons;
    }


    @NonNull
    @Override
    public PokedexPokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardListPokedexItemBinding binding = CardListPokedexItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new PokedexPokemonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PokedexPokemonViewHolder holder, int position) {
        PokedexData currentPokemon = pokemons.get(position);
        holder.pokemonName.setText(currentPokemon.getName());
        //holder.pokemonImage.setImageResource(currentPokemon.getImage());

    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }


    public class PokedexPokemonViewHolder extends RecyclerView.ViewHolder {
        private ImageView pokemonImage;
        private TextView pokemonName;
        private MaterialCardView pokemonCard;

        public PokedexPokemonViewHolder(@NonNull CardListPokedexItemBinding binding) {
            super(binding.getRoot());
            pokemonImage = binding.ivPokedexItemList;
            pokemonName = binding.tvPokedexItemListName;
            pokemonCard = binding.cvPokedexList;
        }
    }
}
