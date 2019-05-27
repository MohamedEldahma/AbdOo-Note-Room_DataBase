/*
 * Copyright (c) 2019.
 * AbdOo Saed
 * abdoo.dev@gmail.com
 */

package com.example.myroom.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.myroom.DataBase.DAOs.TodoDAO;
import com.example.myroom.DataBase.Model.TodoModel;

@Database(entities = {TodoModel.class}, version = 1, exportSchema = false)

public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase myDatabase;

    public static MyDatabase getInstance(Context context) {
        if (myDatabase == null) {
            myDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    MyDatabase.class, "Todo_DataBase").allowMainThreadQueries().build();
        }
        return myDatabase;
    }

    //Singleton Pattern
    public abstract TodoDAO todoDAO();

}
