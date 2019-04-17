package com.example.fruitsapp.viewmodel;

import android.app.Application;

import com.example.fruitsapp.databases.fruit.FruitRepository;
import com.example.fruitsapp.model.Fruit;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class FruitViewModel extends AndroidViewModel {

    private FruitRepository repository;
    private LiveData<List<Fruit>> allFruits;
    private LiveData<List<Fruit>> allFruitsFromCart;

    public FruitViewModel(@NonNull Application application) {
        super(application);
        repository = new FruitRepository(application);
        allFruits = repository.getAllFruits();
        allFruitsFromCart = repository.getAllFruitsFromCart();
    }

    public void insert(Fruit fruit) {
        repository.insert(fruit);
    }

    public void update(Fruit fruit) {
        repository.update(fruit);
    }

    public void delete(Fruit fruit) {
        repository.delete(fruit);
    }

    public LiveData<List<Fruit>> getAllFruits() {
        return allFruits;
    }

    public LiveData<List<Fruit>> getAllFruitsFromCart() {
        return allFruitsFromCart;
    }
}
