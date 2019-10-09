package pl.amad.drinky.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import pl.amad.drinky.data.model.Party;


@Dao
public interface UserDao {
    @Query("SELECT * FROM " + Party.TABLE_NAME)
    List<Party> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Party party);

    @Query("SELECT * FROM " + Party.TABLE_NAME + " WHERE id = :id")
    Party searchById(long id);

    @Query("SELECT * FROM " + Party.TABLE_NAME + " WHERE name = :login")
    Party searchByUsername(String login);

    @Query("SELECT COUNT(*) FROM " + Party.TABLE_NAME)
    int getDataCount();

}
