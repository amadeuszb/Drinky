package pl.amad.drinky.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import pl.amad.drinky.data.model.Party;

@Database(entities = {Party.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase db;

    public synchronized static UserDatabase getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context, UserDatabase.class, Party.TABLE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return db;
    }

    public abstract UserDao dao();
}
