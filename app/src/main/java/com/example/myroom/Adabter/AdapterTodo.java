/*
 * Copyright (c) 2019.
 * AbdOo Saed
 * abdoo.dev@gmail.com
 */

package com.example.myroom.Adabter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myroom.DataBase.Model.TodoModel;
import com.example.myroom.Edit_ItemRecylerVActivity;
import com.example.myroom.R;

import java.util.List;

public class AdapterTodo extends RecyclerView.Adapter<AdapterTodo.ViewHolder> {
    List<TodoModel> todoModelList;
    private Context context;

    public AdapterTodo(List<TodoModel> todoModelList, Context context) {
        this.todoModelList = todoModelList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_todo, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final TodoModel todoModel = todoModelList.get(i);
        viewHolder.tv_item_data.setText(todoModel.getDateTime());
        viewHolder.tv_item_title.setText(todoModel.getTitle());
        viewHolder.tv_item_content.setText(todoModel.getContent());
        viewHolder.layout_data_racyclerV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Edit_ItemRecylerVActivity.class);
                Bundle db = new Bundle();
                db.putSerializable("todoModel", todoModel);
                intent.putExtras(db);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (todoModelList == null) {
            return 0;
        }
        return todoModelList.size();
    }

    public void changeDate(List<TodoModel> list) {
        todoModelList = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_item_data, tv_item_title, tv_item_content;
        private LinearLayout layout_data_racyclerV;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_item_title = itemView.findViewById(R.id.tv_item_title);
            tv_item_data = itemView.findViewById(R.id.tv_item_data);
            tv_item_content = itemView.findViewById(R.id.tv_item_content);
            layout_data_racyclerV = itemView.findViewById(R.id.layout_data_racyclerV);
        }
    }
}
