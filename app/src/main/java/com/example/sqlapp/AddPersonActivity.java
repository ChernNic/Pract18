package com.example.sqlapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddPersonActivity extends AppCompatActivity {

    ImageButton BackBtn;
    EditText nameEditText, ageEditText, occupationEditText, addressEditText;
    Button addPersonButton;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        dbHelper = new DatabaseHelper(this);


        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        occupationEditText = findViewById(R.id.occupationEditText);
        addressEditText = findViewById(R.id.addressEditText);
        addPersonButton = findViewById(R.id.addPersonButton);
        BackBtn = findViewById(R.id.backButton);

        BackBtn.setOnClickListener(view -> {
            Intent intent = new Intent(AddPersonActivity.this, MainActivity.class);
            startActivity(intent);
        });

        addPersonButton.setOnClickListener(view -> {
            String name = nameEditText.getText().toString();
            int age = Integer.parseInt(ageEditText.getText().toString());
            String occupation = occupationEditText.getText().toString();
            String address = addressEditText.getText().toString();

            Person newPerson = new Person( name, age, occupation, address);

            dbHelper.addPerson(newPerson);

            nameEditText.setText("");
            ageEditText.setText("");
            occupationEditText.setText("");
            addressEditText.setText("");

            Toast.makeText(getApplicationContext(),"Person Added", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AddPersonActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
