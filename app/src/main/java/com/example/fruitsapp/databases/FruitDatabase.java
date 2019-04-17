package com.example.fruitsapp.databases;

import android.content.Context;
import android.os.AsyncTask;

import com.example.fruitsapp.databases.fruit.FruitDao;
import com.example.fruitsapp.model.Fruit;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Fruit.class}, version = 1)
public abstract class FruitDatabase extends RoomDatabase {

    public static FruitDatabase instance;

    public abstract FruitDao fruitDao();

    private static RoomDatabase.Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsynctask(instance).execute();
        }
    };

    public static synchronized FruitDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    FruitDatabase.class,
                    "fruit_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static class PopulateDbAsynctask extends AsyncTask<Void, Void, Void> {

        private FruitDao fruitDao;

        public PopulateDbAsynctask(FruitDatabase fruitDatabase) {
            this.fruitDao = fruitDatabase.fruitDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            fruitDao.insert(new Fruit("Apple", 0, 1, true));
            fruitDao.insert(new Fruit("Mango", 15, 0, false));
            fruitDao.insert(new Fruit("Banana", 20, 0, false));
            fruitDao.insert(new Fruit("Papaya", 25, 3, true));
            fruitDao.insert(new Fruit("Chickoo", 35, 12, true));
            fruitDao.insert(new Fruit("ABC", 50, 0, false));
            return null;
        }
    }

}
