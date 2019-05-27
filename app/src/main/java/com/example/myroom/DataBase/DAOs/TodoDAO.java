package com.example.myroom.DataBase.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.myroom.DataBase.Model.TodoModel;

import java.util.List;

@Dao//Data Access Object
public interface TodoDAO {
    @Insert
     void addTodo(TodoModel todoModel);

    @Delete
     void deletTodo(TodoModel todoModel);

    @Update
     void updateTodo(TodoModel todoModel);

    @Query("Select * from todomodel;")
    List<TodoModel> getAllTodo();
}
