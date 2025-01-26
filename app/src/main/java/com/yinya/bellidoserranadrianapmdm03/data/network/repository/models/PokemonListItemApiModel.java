package com.yinya.bellidoserranadrianapmdm03.data.network.repository.models;

public class PokemonListItemApiModel {
    int id;
    String name;
    Boolean captureState;

    public PokemonListItemApiModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public Boolean getCaptureState() {
        return captureState;
    }

    public void setCaptureState(Boolean capturedState) {
        this.captureState = capturedState;
    }
}
