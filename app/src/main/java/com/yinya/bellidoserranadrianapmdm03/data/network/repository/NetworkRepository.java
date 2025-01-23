package com.yinya.bellidoserranadrianapmdm03.data.network.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.IPokemonApi;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.PokemonApiService;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models.PokemonDetailResponseDataClass;
import com.yinya.bellidoserranadrianapmdm03.data.network.pokemonApi.models.PokemonResponseDataClass;
import com.yinya.bellidoserranadrianapmdm03.data.network.repository.models.PokemonDetailApiModel;
import com.yinya.bellidoserranadrianapmdm03.data.network.repository.models.PokemonListItemApiModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkRepository {
    private static final String USER_COLLECTION = "users";
    public static FirebaseFirestore firebaseDb;
    public static DocumentReference userDocument;
    private static NetworkRepository instance;
    private static IPokemonApi apiService;
    private static List<PokemonDetailApiModel> _capturedPokemonsList = new ArrayList<>();
    private MutableLiveData<List<PokemonListItemApiModel>> _pokemons = new MutableLiveData(null);
    private MutableLiveData<List<PokemonDetailApiModel>> _capturedPokemons = new MutableLiveData(null);

    private NetworkRepository() {
        PokemonApiService service = PokemonApiService.getInstance();
        apiService = service.getRetrofitService();
        initFirebaseDatabase();
    }

    private void initFirebaseDatabase() {
        firebaseDb = FirebaseFirestore.getInstance();
        CollectionReference collection = firebaseDb.collection(USER_COLLECTION);
        getUserDocument(collection, "VZOUAKMtMTTw1aD3uWhgLRxI26F2");
    }

    public static NetworkRepository getInstance() {
        if (instance == null) {
            instance = new NetworkRepository();
        }
        return instance;
    }

    public void getUserDocument(CollectionReference collection, String userId) {
        userDocument = collection.document(userId);
        userDocument.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> data = document.getData();

                        if (data != null && data.containsKey("pokemons")) {
                            List<Map<String, Object>> pokemonsData = (List) data.get("pokemons");
                            for (Map<String, Object> p : pokemonsData) {
                                PokemonDetailApiModel pokemon = new PokemonDetailApiModel();
                                pokemon.setId(((Number) p.get("id")).intValue());
                                pokemon.setName((String) p.get("name"));
                                pokemon.setWeight(((Number) p.get("weight")).floatValue());
                                pokemon.setHeight(((Number) p.get("height")).floatValue());
                                pokemon.setType1((String) p.get("type1"));
                                pokemon.setType2((String) p.get("type2"));
                                pokemon.setFrontDefault((String) p.get("frontDefault"));
                                pokemon.setOfficialArtwork((String) p.get("officialArtwork"));
                                _capturedPokemonsList.add(pokemon);
                                _capturedPokemons.setValue(_capturedPokemonsList);
                            }
                        }
                    } else {
                        Log.d("networkRepository", "No such document");
                    }
                } else {
                    Log.d("networkRepository", "get failed with ", task.getException());
                }
            }
        });
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
                    List<PokemonListItemApiModel> pokemonList = pokemonResponse.asPokemonListApiModel();
                    _pokemons.setValue(pokemonList);
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
                    _capturedPokemons.setValue(_capturedPokemonsList);
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

    public void addPokemonsToUser(Map<String, List<PokemonDetailApiModel>> pokemons) {
        userDocument.set(pokemons)
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

/*    public ArrayList getUserCapturedPokemons(String userId) {

    }*/
}
