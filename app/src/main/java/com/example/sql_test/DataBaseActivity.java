package com.example.sql_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.sql_test.data.DbHelper;
import com.example.sql_test.databinding.ActivityDatabaseBinding;

public class DataBaseActivity extends AppCompatActivity {
    ActivityDatabaseBinding binding;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDatabaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new DbHelper(this);

        String data = db.getData();
        binding.viewData.setText(data);
    }
}