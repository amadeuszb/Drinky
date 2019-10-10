package pl.amad.drinky.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import pl.amad.drinky.data.model.Party;

@Database(entities = {Party.class}, version = 2, exportSchema = false)
public abstract class PartyDatabase extends RoomDatabase {
    private static PartyDatabase db;

    public synchronized static PartyDatabase getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context, PartyDatabase.class, Party.TABLE_NAME)
                    .allowMainThreadQueries().fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }

    public abstract PartyDao dao();
}
