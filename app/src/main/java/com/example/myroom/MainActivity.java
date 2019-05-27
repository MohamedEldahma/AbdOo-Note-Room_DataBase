/*
 * Copyright (c) 2019.
 * AbdOo Saed
 * abdoo.dev@gmail.com
 */

package com.example.myroom;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myroom.Adabter.AdapterTodo;
import com.example.myroom.Base.BaseSharedPreference;
import com.example.myroom.DataBase.Model.TodoModel;
import com.example.myroom.DataBase.MyDatabase;

import java.util.Collections;
import java.util.List;

public class MainActivity extends BaseSharedPreference {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterTodo adapterTodo;
    private FloatingActionButton fab_add;
    private Toolbar toolbarHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        adapterTodo = new AdapterTodo(null, this);
        recyclerView.setAdapter(adapterTodo);
        recyclerView.setLayoutManager(layoutManager);
        setSupportActionBar(toolbarHome);
        getSupportActionBar().setTitle(getShardPreference("name") + " Note");//getString(R.string.app_name)
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    fab_add.hide();
                } else {
                    fab_add.show();
                }

                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();
        if (id == R.id.Exit) {
            finish();
        } else if (id == R.id.About_Us) {
        startActivity(new Intent(getApplicationContext(), AboutActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }

    public void gotoadd(View view) {
        startActivity(new Intent(getApplicationContext(), AddTodoAct.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<TodoModel> todoList = MyDatabase.getInstance(getApplicationContext()).todoDAO()
                .getAllTodo();
        Collections.reverse(todoList);
        adapterTodo.changeDate(todoList);
    }

    private void initView() {
        fab_add = findViewById(R.id.fab_add);
        toolbarHome = findViewById(R.id.toolbarHome);
    }

    public void editUserName(MenuItem item) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Enter Your Name");
        final EditText input = new EditText(MainActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);
        alertDialog.setPositiveButton("Set Name",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        setShardPreference("name", input.getText().toString());
                        getSupportActionBar().setTitle(input.getText().toString() + " Note");
                    }
                });


        alertDialog.show();
    }
}
