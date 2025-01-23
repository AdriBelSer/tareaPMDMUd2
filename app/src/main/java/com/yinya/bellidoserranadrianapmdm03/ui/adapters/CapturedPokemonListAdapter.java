package com.yinya.bellidoserranadrianapmdm03.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.yinya.bellidoserranadrianapmdm03.R;
import com.yinya.bellidoserranadrianapmdm03.databinding.CardListCapturedPokemonItemBinding;
import com.yinya.bellidoserranadrianapmdm03.ui.MainActivity;
import com.yinya.bellidoserranadrianapmdm03.ui.models.CapturedPokemonData;

import java.util.ArrayList;

public class CapturedPokemonListAdapter extends RecyclerView.Adapter<CapturedPokemonListAdapter.CapturedPokemonViewHolder> {

    private ArrayList<CapturedPokemonData> capturedPokemons;
    private final Context context;

    public CapturedPokemonListAdapter(ArrayList<CapturedPokemonData> capturedPokemons, Context context) {
        this.capturedPokemons = capturedPokemons;
        this.context = context;
    }

    @NonNull
    @Override
    public CapturedPokemonListAdapter.CapturedPokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardListCapturedPokemonItemBinding binding = CardListCapturedPokemonItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CapturedPokemonListAdapter.CapturedPokemonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CapturedPokemonListAdapter.CapturedPokemonViewHolder holder, int position) {
        CapturedPokemonData currentPokemon = capturedPokemons.get(position);
        holder.tvName.setText(currentPokemon.getName());
        String pokemonId = context.getString(R.string.pokemon_captured_pokemon_id, Integer.toString(currentPokemon.getId()));
        holder.tvId.setText(pokemonId);
        holder.tvTypes.setText(currentPokemon.getTypes());
        holder.tvHeight.setText(String.format("%.0f", currentPokemon.getHeight()));
        holder.tvWeight.setText(String.format("%.1f", currentPokemon.getWeight()));
        String imageUrl = currentPokemon.getImage();
        Glide.with(context).load(imageUrl).into(holder.ivImage);
        holder.pokemonCard.setOnClickListener(v -> {
            ((MainActivity) context).showPokemonDetail(currentPokemon.getId(), v);
            Log.d("captured", "selected");
        });
    }

    @Override
    public int getItemCount() {
        return capturedPokemons.size();
    }


    public class CapturedPokemonViewHolder extends RecyclerView.ViewHolder {
        private MaterialCardView pokemonCard;
        private TextView tvId;
        private TextView tvName;
        private TextView tvTypes;
        private TextView tvHeight;
        private TextView tvWeight;
        private ImageView ivImage;

        public CapturedPokemonViewHolder(@NonNull CardListCapturedPokemonItemBinding binding) {
            super(binding.getRoot());
            pokemonCard = binding.mcvCapturedPokemonList;
            tvId = binding.tvCapturedPokemonItemId;
            tvName = binding.tvCapturedPokemonItemName;
            tvTypes = binding.tvCapturedPokemonItemTypeList;
            tvHeight = binding.tvCapturedPokemonItemHeightAmount;
            tvWeight = binding.tvCapturedPokemonItemWeightAmount;
            ivImage = binding.ivCapturedPokemonItem;
        }
    }
}
