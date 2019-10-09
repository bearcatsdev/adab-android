package com.ambinusian.adab.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ClassEntity.class}, version = 1)
public abstract class ClassDatabase extends RoomDatabase {
    public abstract ClassDAO classDAO();

    private static volatile ClassDatabase INSTANCE;

    public static ClassDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ClassDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ClassDatabase.class, "class_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
