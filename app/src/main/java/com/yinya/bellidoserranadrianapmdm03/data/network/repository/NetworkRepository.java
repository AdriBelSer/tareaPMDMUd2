package com.yinya.bellidoserranadrianapmdm03.data.network.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.IPokemonApi;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.PokemonApiService;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models.PokemonDetailResponseDataClass;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models.PokemonResponseDataClass;
import com.yinya.bellidoserranadrianapmdm03.data.network.repository.models.PokemonDetailApiModel;
import com.yinya.bellidoserranadrianapmdm03.data.network.repository.models.PokemonListItemApiModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkRepository {
    private static final String USER_COLLECTION = "users";
    private static NetworkRepository instance;
    private final IPokemonApi apiService;
    private final MutableLiveData<List<PokemonListItemApiModel>> _pokemons = new MutableLiveData<>(null);
    private final MutableLiveData<List<PokemonDetailApiModel>> _capturedPokemons = new MutableLiveData<>(null);
    private final MutableLiveData<Boolean> _loginStatus = new MutableLiveData<>(null);
    public DocumentReference userDocument;
    public FirebaseFirestore firebaseDb;
    public String userId;
    private List<PokemonListItemApiModel> _pokemonsList = new ArrayList<>();
    private List<PokemonDetailApiModel> _capturedPokemonsList = new ArrayList<>();

    private NetworkRepository() {
        PokemonApiService service = PokemonApiService.getInstance();
        apiService = PokemonApiService.getRetrofitService();
    }

    public static NetworkRepository getInstance() {
        if (instance == null) {
            instance = new NetworkRepository();
        }
        return instance;
    }

    public void initFirebaseDatabase(String userId) {
        this.userId = userId;
        firebaseDb = FirebaseFirestore.getInstance();
        getUserDocument();
    }

    private void createUserInFirestore(FirebaseUser user, DocumentReference usersCollectionRef) {

        Map<String, Object> userMap = new HashMap<>();
        List<Map<String, Object>> pokemons = new ArrayList<>();
        userMap.put("name", user.getDisplayName());
        userMap.put("email", user.getEmail());
        userMap.put("createdAt", FieldValue.serverTimestamp());
        userMap.put("pokemons", pokemons);

        usersCollectionRef.set(userMap).addOnSuccessListener(aVoid -> {
            Log.d("Firestore", "User created successfully");
            _loginStatus.setValue(true);
        }).addOnFailureListener(e -> {
            Log.e("Firestore", "Error creating user", e);
        });
    }

    public void getUserDocument() {
        CollectionReference collection = firebaseDb.collection(USER_COLLECTION);
        userDocument = collection.document(userId);
        userDocument.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Map<String, Object> data = document.getData();

                    if (data != null && data.containsKey("pokemons")) {
                        List<Map<String, Object>> pokemonsData = (List) data.get("pokemons");
                        if (pokemonsData != null) {
                            for (Map<String, Object> p : pokemonsData) {
                                PokemonDetailApiModel pokemon = new PokemonDetailApiModel(p);
                                _capturedPokemonsList.add(pokemon);
                            }
                        }
                        fillCapturedPokemons();
                        _loginStatus.setValue(true);
                    }
                } else if (user != null) {
                    createUserInFirestore(user, userDocument);
                } else {
                    Log.d("networkRepository", "No such document");
                }
            } else {
                Log.d("networkRepository", "get failed with ", task.getException());
            }
        });
    }

    private void fillCapturedPokemons() {
        for (PokemonListItemApiModel p : this._pokemonsList) {
            boolean found = _capturedPokemonsList.stream().anyMatch(pokemon -> pokemon.getId() == p.getId());
            p.setCaptureState(found);
        }
        _pokemons.setValue(this._pokemonsList);
        _capturedPokemons.setValue(_capturedPokemonsList);
    }

    public void fetchPokemonsFromApi() {

        // Llamar al método fetchPokemon
        Call<PokemonResponseDataClass> call = apiService.fetchPokemonsList(); // ID del Pokémon

        // Ejecutar la llamada de forma asíncrona
        call.enqueue(new Callback<PokemonResponseDataClass>() {
            @Override
            public void onResponse(Call<PokemonResponseDataClass> call, Response<PokemonResponseDataClass> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PokemonResponseDataClass pokemonResponse = response.body();
                    _pokemonsList = pokemonResponse.asPokemonListApiModel();
                    fillCapturedPokemons();
                    Log.d("POKEMON", "Fetch success");
                } else {
                    Log.e("POKEMON", "Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponseDataClass> call, Throwable t) {
                Log.e("POKEMON", "Error al obtener el Pokémon: " + t.getMessage());
            }
        });
    }

    public void fetchOnePokemonFromApi(int id) {

        // Llamar al método fetchPokemon
        Call<PokemonDetailResponseDataClass> call = apiService.fetchPokemon(Integer.toString(id)); // ID del Pokémon

        // Ejecutar la llamada de forma asíncrona
        call.enqueue(new Callback<PokemonDetailResponseDataClass>() {
            @Override
            public void onResponse(Call<PokemonDetailResponseDataClass> call, Response<PokemonDetailResponseDataClass> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PokemonDetailResponseDataClass pokemonResponse = response.body();
                    PokemonDetailApiModel pokemon = pokemonResponse.asPokemonDetailApiModel();
                    _capturedPokemonsList.add(pokemon);
                    setPokemonsToUser(_capturedPokemonsList);
                    fillCapturedPokemons();
                    Log.d("POKEMON", "Fetch success");
                } else {
                    Log.e("POKEMON", "Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PokemonDetailResponseDataClass> call, Throwable t) {
                Log.e("POKEMON", "Error al obtener el Pokémon: " + t.getMessage());
            }
        });
    }

    public LiveData<List<PokemonListItemApiModel>> getAllPokemonsLiveData() {
        return _pokemons;
    }

    public LiveData<List<PokemonDetailApiModel>> getCapturedPokemonsLiveData() {
        return _capturedPokemons;
    }

    public LiveData<Boolean> getLoginStatusLiveData() {
        return _loginStatus;
    }

    public void setPokemonsToUser(List<PokemonDetailApiModel> pokemons) {
        Map<String, Object> userPokemons = new HashMap<>();
        userPokemons.put("pokemons", pokemons);
        userDocument.update(userPokemons)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Firebase", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firebase", "Error writing document", e);
                    }
                });
    }

    public void deleteCapturedPokemon(int id) {
        _capturedPokemonsList.removeIf(pokemon -> pokemon.getId() == id);
        setPokemonsToUser(_capturedPokemonsList);
        fillCapturedPokemons();
    }


    public void resetRepository() {
        _capturedPokemonsList = new ArrayList<>();
        fillCapturedPokemons();
        _loginStatus.setValue(null);
        userId = null;
        userDocument = null;
    }
}
