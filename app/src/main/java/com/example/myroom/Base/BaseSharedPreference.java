package com.example.myroom.Base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class BaseSharedPreference extends AppCompatActivity {
    protected AppCompatActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
    }

    public void setShardPreference(String key, String value) {
        SharedPreferences shr = getSharedPreferences("savenm", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shr.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getShardPreference(String key) {
        SharedPreferences shr = getSharedPreferences("savenm", Context.MODE_PRIVATE);
        String shardPreference = shr.getString(key, "");
        return shardPreference;
    }
}
