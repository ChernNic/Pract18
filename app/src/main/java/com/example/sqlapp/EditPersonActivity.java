    package com.example.sqlapp;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.appcompat.widget.AppCompatButton;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Toast;

    import java.util.ArrayList;

    public class EditPersonActivity extends AppCompatActivity {

        AppCompatButton BackBtn;
        EditText nameEditText, ageEditText, occupationEditText, addressEditText;
        Button EditPersonButton;

        private DatabaseHelper dbHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_person);


            BackBtn = findViewById(R.id.backButton);
            nameEditText = findViewById(R.id.nameEditText);
            ageEditText = findViewById(R.id.ageEditText);
            occupationEditText = findViewById(R.id.occupationEditText);
            addressEditText = findViewById(R.id.addressEditText);
            EditPersonButton = findViewById(R.id.editPersonButton);


            dbHelper = new DatabaseHelper(this);

            int position = 1;
            Intent intent = getIntent();
            if (intent.hasExtra("position")) {
                position = intent.getIntExtra("position", 1);
            }
            ArrayList<Person> personList = dbHelper.getPersonList();
            Person person = personList.get(position);

            nameEditText.setText(person.getName());
            addressEditText.setText(person.getAddress());
            occupationEditText.setText(person.getOccupation());
            ageEditText.setText(String.valueOf(person.getAge()));

            BackBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(EditPersonActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });

            EditPersonButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = nameEditText.getText().toString();
                    int age = Integer.parseInt(ageEditText.getText().toString());
                    String occupation = occupationEditText.getText().toString();
                    String address = addressEditText.getText().toString();

                    Person newPerson = new Person( name, age, occupation, address);

                    dbHelper.updatePerson(newPerson);
                    Toast.makeText(getApplicationContext(),"Person Edited", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onBackPressed() {
            super.onBackPressed();
            Intent intent = new Intent(EditPersonActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }