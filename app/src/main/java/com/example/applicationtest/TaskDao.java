package com.example.applicationtest;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public  interface TaskDao {

    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT * FROM task ORDER BY name")
    List<Task> getNameacending();

    @Query("SELECT * FROM task ORDER BY price")
    List<Task> getPriceacending();

    @Delete
    public void delete(Task task);

    @Insert
    void insert(Task task);

 //   @Query("SELECT SUM(total) FROM task ")
 //   List<Task> getAllSum();

}
