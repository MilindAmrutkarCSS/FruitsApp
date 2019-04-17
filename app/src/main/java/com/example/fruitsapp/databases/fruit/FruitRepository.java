package com.example.fruitsapp.databases.fruit;

import android.app.Application;
import android.os.AsyncTask;

import com.example.fruitsapp.databases.FruitDatabase;
import com.example.fruitsapp.model.Fruit;

import java.util.List;

import androidx.lifecycle.LiveData;

public class FruitRepository {

    private FruitDao fruitDao;
    private LiveData<List<Fruit>> allFruits;
    private LiveData<List<Fruit>> allFruitsFromCart;


    public FruitRepository(Application application) {
        FruitDatabase database = FruitDatabase.getInstance(application);
        fruitDao = database.fruitDao();
        allFruits = fruitDao.getAllFruits();
        allFruitsFromCart = fruitDao.getAllFruitsFromCart();
    }

    public void insert(Fruit fruit) {
        new InsertFruitAsyncTask(fruitDao).execute(fruit);
    }

    public void update(Fruit fruit) {
        new UpdateFruitAsyncTask(fruitDao).execute(fruit);
    }

    public void delete(Fruit fruit) {
        new DeleteFruitAsyncTask(fruitDao).execute(fruit);
    }

    public LiveData<List<Fruit>> getAllFruits() {
        return allFruits;
    }

    public LiveData<List<Fruit>> getAllFruitsFromCart() {
        return allFruitsFromCart;
    }

    private static class InsertFruitAsyncTask extends AsyncTask<Fruit, Void, Void> {

        private FruitDao fruitDao;

        private InsertFruitAsyncTask(FruitDao fruitDao) {
            this.fruitDao = fruitDao;
        }

        @Override
        protected Void doInBackground(Fruit... fruits) {
            fruitDao.insert(fruits[0]);
            return null;
        }
    }

    private static class UpdateFruitAsyncTask extends AsyncTask<Fruit, Void, Void> {

        private FruitDao fruitDao;

        private UpdateFruitAsyncTask(FruitDao fruitDao) {
            this.fruitDao = fruitDao;
        }

        @Override
        protected Void doInBackground(Fruit... fruits) {
            fruitDao.update(fruits[0]);
            return null;
        }
    }

    private static class DeleteFruitAsyncTask extends AsyncTask<Fruit, Void, Void> {

        private FruitDao fruitDao;

        public DeleteFruitAsyncTask(FruitDao fruitDao) {
            this.fruitDao = fruitDao;
        }


        @Override
        protected Void doInBackground(Fruit... fruits) {
            fruitDao.delete(fruits[0]);
            return null;
        }
    }

}
