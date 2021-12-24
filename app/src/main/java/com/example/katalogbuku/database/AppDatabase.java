package com.example.katalogbuku.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.katalogbuku.database.dao.BukuDao;
import com.example.katalogbuku.database.entitas.buku;

@Database(entities = {buku.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static com.example.katalogbuku.database.AppDatabase sInstance;
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();
    @VisibleForTesting
    public static final String DATABASE_NAME= "db_katalogbuku";

    public abstract BukuDao bukuDao();

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    private void updateDatabaseCreated(final Context context){
        if(context.getDatabasePath(DATABASE_NAME).exists()){
            setDatabaseCreated();
        }
    }

    public static com.example.katalogbuku.database.AppDatabase buildDatabase(final Context context){
        return Room.databaseBuilder(context, com.example.katalogbuku.database.AppDatabase.class,DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        com.example.katalogbuku.database.AppDatabase database = com.example.katalogbuku.database.AppDatabase.getInstance(context);
                        database.setDatabaseCreated();
                    }
                }).allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    public static com.example.katalogbuku.database.AppDatabase getInstance(Context context) {
        if(sInstance == null){
            synchronized (com.example.katalogbuku.database.AppDatabase.class){
                if(sInstance == null){
                    sInstance = buildDatabase(context);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }
}
