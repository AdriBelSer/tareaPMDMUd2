package com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models;

import com.google.gson.annotations.SerializedName;

public class PokemonSpritesResponseDataClass {
    @SerializedName("front_default")
    private String frontDefault;

    public PokemonSpritesResponseDataClass(String frontDefault) {
        this.frontDefault = frontDefault;
    }
}
