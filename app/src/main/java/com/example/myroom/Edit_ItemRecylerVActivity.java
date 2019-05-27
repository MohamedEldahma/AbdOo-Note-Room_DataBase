/*
 * Copyright (c) 2019.
 * AbdOo Saed
 * abdoo.dev@gmail.com
 */

package com.example.myroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.myroom.DataBase.Model.TodoModel;
import com.example.myroom.DataBase.MyDatabase;

public class Edit_ItemRecylerVActivity extends AppCompatActivity {
    private TodoModel todoModel;
    private EditText edt_title_upd, edt_note_upd;
    private String strDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__item_recyler_v);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        todoModel = (TodoModel) bundle.getSerializable("todoModel");
        edt_note_upd = findViewById(R.id.edt_note_upd);
        edt_title_upd = findViewById(R.id.edt_title_upd);
        edt_title_upd.setText(todoModel.getTitle());
        edt_note_upd.setText(todoModel.getContent());
        strDate = todoModel.getDateTime();
    }

    public void btnDeleteOnClick(View view) {
        MyDatabase.getInstance(this).todoDAO().deletTodo(todoModel);
        finish();
    }

    public void btnUpdateOnClick(View view) {
        if (TextUtils.isEmpty(edt_title_upd.getText().toString())) {
            edt_title_upd.setError("Enter ur Title Plzz");
        } else if (TextUtils.isEmpty(edt_note_upd.getText().toString())) {
            edt_note_upd.setError("Enter ur Note Plzz");
        } else {
            todoModel.setContent(edt_note_upd.getText().toString());
            todoModel.setTitle(edt_title_upd.getText().toString());
            MyDatabase.getInstance(this).todoDAO().updateTodo(todoModel);
            finish();
        }
    }
}
