package com.example.manager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewWorker extends AppCompatActivity {

    private EditText editName, editLastName, editPhone, editEmail;
    private String name, lastName, phone, email, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_worker);

        editName = (EditText) findViewById(R.id.editName);
        editLastName = (EditText) findViewById(R.id.editLastName);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editEmail = (EditText) findViewById(R.id.editEmail);
    }

    public void add(View view) {
        name = editName.getText().toString().trim().toLowerCase();
        lastName = editLastName.getText().toString().trim().toLowerCase();
        phone = editPhone.getText().toString().trim();
        email = editEmail.getText().toString().trim();


        if (name.isEmpty() || lastName.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            Toast.makeText(NewWorker.this, "Uzupełnij dane", Toast.LENGTH_LONG).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(NewWorker.this, "Podaj poprawny email", Toast.LENGTH_LONG).show();
        } else if (!Patterns.PHONE.matcher(phone).matches()) {
            Toast.makeText(NewWorker.this, "Podaj poprawny numer", Toast.LENGTH_LONG).show();
        } else {
            saveData();
        }
    }


    public void saveData() {

        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);

        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        DatabaseReference mFirebaseDatabase = mFirebaseInstance.getReference("workers");

        if (TextUtils.isEmpty(id)) {
            id = mFirebaseDatabase.push().getKey();
        }

        WorkersUpload result = new WorkersUpload(name, lastName, phone, email);
        mFirebaseDatabase.child(id).setValue(result);

        finish();
    }
}
