package com.example.sqlapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private AppCompatButton BackBtn;
    private TextView nameTextView;
    private TextView ageTextView;
    private TextView occupationTextView;
    private TextView addressTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nameTextView = findViewById(R.id.nameTextView);
        ageTextView = findViewById(R.id.ageTextView);
        occupationTextView = findViewById(R.id.occupationTextView);
        addressTextView = findViewById(R.id.addressTextView);
        BackBtn = findViewById(R.id.backButton);

        int position = 1;
        Intent intent = getIntent();
        if (intent.hasExtra("position")) {
            position = intent.getIntExtra("position", 1);
        }

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        ArrayList<Person> personList = dbHelper.getPersonList();
        Person person = personList.get(position);

        nameTextView.setText(person.getName());
        ageTextView.setText(String.valueOf(person.getAge()));
        occupationTextView.setText(person.getOccupation());
        addressTextView.setText(person.getAddress());

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}