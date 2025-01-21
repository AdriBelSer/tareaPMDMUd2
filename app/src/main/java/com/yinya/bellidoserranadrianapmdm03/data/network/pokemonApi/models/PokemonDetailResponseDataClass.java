package com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models;

import com.google.gson.annotations.SerializedName;
import com.yinya.bellidoserranadrianapmdm03.data.network.repository.models.PokemonDetailApiModel;
import com.yinya.bellidoserranadrianapmdm03.data.network.repository.models.PokemonListItemApiModel;

import java.util.ArrayList;
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

    public PokemonDetailApiModel asPokemonDetailApiModel() {
        int id = this.getId();
        String name = this.getName();
        int hectogramsWeight = this.getWeight();
        int decimetersHeight = this.getHeight();
        float weight = hectogramsWeight / 10f;
        float height = decimetersHeight * 10f;
        List<PokemonDetailResponseDataClass. PokemonTypeResponseDataClass> types = this.getTypes();
        String type1 = types.size() > 0 ? types.get(0).type.name : "";
        String type2 = types.size() > 1 ? types.get(1).type.name : "";
        String frontDefault = this.getSprites().getFrontDefault();
        String officialArtwork = this.getSprites().getOfficialArtwork();
        return new PokemonDetailApiModel(id, name, weight, height, type1, type2, frontDefault, officialArtwork);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public PokemonSpritesResponseDataClass getSprites() {
        return sprites;
    }

    public void setSprites(PokemonSpritesResponseDataClass sprites) {
        this.sprites = sprites;
    }

    public List<PokemonTypeResponseDataClass> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonTypeResponseDataClass> types) {
        this.types = types;
    }

    public static class PokemonSpritesResponseDataClass {

        OtherSprites other;
        @SerializedName("front_default")
        private String frontDefault;

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
