package com.example.myroom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myroom.DataBase.Model.TodoModel;
import com.example.myroom.DataBase.MyDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddTodoAct extends AppCompatActivity implements View.OnClickListener {

    /**
     * Title
     */
    private EditText edTitle;

    /**
     * Content
     */
    private EditText edContent;
    /**
     * Add
     */
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        initView();

    }

    private void initView() {
        edTitle = findViewById(R.id.ed_title);
        edContent = findViewById(R.id.ed_content);
        add = findViewById(R.id.add);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String strTitle = edTitle.getText().toString();
        String strContent = edContent.getText().toString();
//                String strDate=edDate.getText().toString();
        SimpleDateFormat formatter = new SimpleDateFormat("M/dd hh:mm a", new Locale("EN"));
        String strDate = formatter.format(new Date());
        TodoModel todoModel = new TodoModel(strTitle, strContent, strDate);
        MyDatabase.getInstance(this).todoDAO().addTodo(todoModel);
        finish();
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
    }
}
