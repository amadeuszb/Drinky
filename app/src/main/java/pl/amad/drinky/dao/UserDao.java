package pl.amad.drinky.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import pl.amad.drinky.data.model.User;


@Dao
public interface UserDao {
    @Query("SELECT * FROM " + User.TABLE_NAME)
    List<User> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    @Query("SELECT * FROM " + User.TABLE_NAME + " WHERE id = :id")
    User searchById(long id);

    @Query("SELECT * FROM " + User.TABLE_NAME + " WHERE login = :login")
    User searchByUsername(String login);

    @Query("SELECT COUNT(*) FROM " + User.TABLE_NAME)
    int getDataCount();

}
