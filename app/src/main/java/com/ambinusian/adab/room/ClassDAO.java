package com.ambinusian.adab.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ClassDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertClass(ClassEntity entity);

    @Query("SELECT * FROM classentity ORDER BY datetime(sessionStartDate) ASC")
    LiveData<List<ClassEntity>> getAllClass();

    @Query("DELETE FROM classentity")
    void deleteAll();
}
