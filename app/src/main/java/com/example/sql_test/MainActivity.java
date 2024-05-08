package com.example.sql_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

import com.example.sql_test.data.DbHelper;
import com.example.sql_test.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityMainBinding binding;

    DbHelper db;

    String id, name, email, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnInsert.setOnClickListener(this);
        binding.btnView.setOnClickListener(this);
        binding.btnDelete.setOnClickListener(this);
        binding.btnUpdate.setOnClickListener(this);
        binding.btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btnInsert.getId()){
            name = binding.editName.getText().toString();
            email = binding.editEmail.getText().toString();
            phone = binding.editMobile.getText().toString();

            if(name.equals("") | email.equals("") | phone.equals("")){
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }
            else {
                db.insertStudent(name, email, phone);
                binding.editId.setText("");
                binding.editName.setText("");
                binding.editEmail.setText("");
                binding.editMobile.setText("");
                Toast.makeText(this, "Saved successfully!", Toast.LENGTH_SHORT).show();
            }
        }

        if(v.getId() == binding.btnView.getId()){
            Intent intent = new Intent(getApplicationContext(), DataBaseActivity.class);
            startActivity(intent);
        }

        if(v.getId() == binding.btnDelete.getId()){
            id = binding.editId.getText().toString().trim();
            if(id.equals("")){
                Toast.makeText(this, "Please fill the ID!", Toast.LENGTH_SHORT).show();
            }
            else{
                long l = Long.parseLong(id);
                db.deleteStudent(l);
                binding.editId.setText("");
                binding.editName.setText("");
                binding.editEmail.setText("");
                binding.editMobile.setText("");
                Toast.makeText(this, "Deleted successfully!", Toast.LENGTH_SHORT).show();
            }
        }

        if(v.getId() == binding.btnUpdate.getId()){
            id = binding.editId.getText().toString().trim();
            name = binding.editName.getText().toString();
            email = binding.editEmail.getText().toString();
            phone = binding.editMobile.getText().toString();

            if(id.equals("") | name.equals("") | email.equals("") | phone.equals("")){
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }
            else {
                long l = Long.parseLong(id);
                db.updateStudent(l, name, email, phone);
                binding.editId.setText("");
                binding.editName.setText("");
                binding.editEmail.setText("");
                binding.editMobile.setText("");
                Toast.makeText(this, "Updated successfully!", Toast.LENGTH_SHORT).show();
            }
        }

        if(v.getId() == binding.btnSearch.getId()){
            id = binding.editId.getText().toString().trim();
            if(id.equals("")){
                Toast.makeText(this, "Please fill the ID!", Toast.LENGTH_SHORT).show();
            }
            else {
                try {
                    long l = Long.parseLong(id);
                    name = db.getName(l);
                    email = db.getEmail(l);
                    phone = db.getMobile(l);

                    binding.editName.setText(name);
                    binding.editEmail.setText(email);
                    binding.editMobile.setText(phone);
                    Toast.makeText(this, "Searched successfully!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(this, "ID is not available!", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}