package com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonDetailResponseDataClass {
    private int id;
    private String name;
    private int weight;
    private int height;
    private PokemonSpritesResponseDataClass sprites;
    private List<PokemonTypeResponseDataClass> types;

    public PokemonDetailResponseDataClass(int id, String name, int weight, int height, PokemonSpritesResponseDataClass sprites, List<PokemonTypeResponseDataClass> types) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.sprites = sprites;
        this.types = types;
    }

    public static class PokemonSpritesResponseDataClass {

        @SerializedName("front_default")
        private String frontDefault;

        OtherSprites other;

        public PokemonSpritesResponseDataClass(String frontDefault, OtherSprites other) {
            this.frontDefault = frontDefault;
            this.other = other;
        }

        public String getFrontDefault() {
            return frontDefault;
        }

        public String getOfficialArtwork() {
            return other.officialArtwork.frontDefault;
        }

        public static class OtherSprites {
            @SerializedName("official-artwork")
            OtherSprites.OfficialArtwork officialArtwork;

            public OtherSprites(OtherSprites.OfficialArtwork officialArtwork) {
                this.officialArtwork = officialArtwork;
            }

            public static class OfficialArtwork {
                @SerializedName("front_default")
                private String frontDefault;

                public OfficialArtwork(String frontDefault) {
                    this.frontDefault = frontDefault;
                }
            }
        }
    }

    public static class PokemonTypeResponseDataClass {
        private PokemonNameTypeResponseDataClass type;

        public PokemonTypeResponseDataClass(PokemonNameTypeResponseDataClass type) {
            this.type = type;
        }

        public static class PokemonNameTypeResponseDataClass {
            private String name;

            public PokemonNameTypeResponseDataClass(String name) {
                this.name = name;
            }
        }
    }

}
