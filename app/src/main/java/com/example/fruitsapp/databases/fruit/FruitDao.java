package com.example.fruitsapp.databases.fruit;

import com.example.fruitsapp.model.Fruit;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface FruitDao {

    @Insert
    void insert(Fruit fruit);

    @Update
    void update(Fruit fruit);

    @Delete
    void delete(Fruit fruit);

    @Query("SELECT * FROM fruit_table WHERE count > 0")
    LiveData<List<Fruit>> getAllFruits();

    @Query("SELECT * FROM fruit_table WHERE inCart")
    LiveData<List<Fruit>> getAllFruitsFromCart();

}
