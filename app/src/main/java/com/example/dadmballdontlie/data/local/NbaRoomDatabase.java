package com.example.dadmballdontlie.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dadmballdontlie.data.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class NbaRoomDatabase extends RoomDatabase {

    private static NbaRoomDatabase database;

    public abstract NbaDAO nbaDAO();

    public static synchronized NbaRoomDatabase getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(
                    context,
                    NbaRoomDatabase.class,
                    "user"
            ).build();
        }

        return database;
    }

}
