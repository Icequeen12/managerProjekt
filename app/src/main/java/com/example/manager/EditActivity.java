package com.example.manager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditActivity extends AppCompatActivity {

    private EditText editName, editLastName, editPhone, editEmail;
    private String name, lastName, phone, email, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editName = (EditText) findViewById(R.id.editName);
        editLastName = (EditText) findViewById(R.id.editLastName);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editEmail = (EditText) findViewById(R.id.editEmail);

        name = getIntent().getExtras().getString("name");
        lastName = getIntent().getExtras().getString("lastName");
        phone = getIntent().getExtras().getString("phone");
        email = getIntent().getExtras().getString("email");
        id = getIntent().getExtras().getString("id");

        editName.setText(name);
        editLastName.setText(lastName);
        editPhone.setText(phone);
        editEmail.setText(email);
    }

    public void onClickOk(View view) {

        name = editName.getText().toString().toLowerCase();
        lastName = editLastName.getText().toString().toLowerCase();
        phone = editPhone.getText().toString();
        email = editEmail.getText().toString();

        if (name.isEmpty() || lastName.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            Toast.makeText(EditActivity.this, "Uzupełnij dane", Toast.LENGTH_LONG).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(EditActivity.this, "Podaj poprawny email", Toast.LENGTH_LONG).show();
        } else if (!Patterns.PHONE.matcher(phone).matches()) {
            Toast.makeText(EditActivity.this, "Podaj poprawny numer", Toast.LENGTH_LONG).show();
        } else {
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);

            WorkersUpload worker = new WorkersUpload(name, lastName, phone, email);
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("workers").child(id);
            databaseReference.setValue(worker);
            finish();
        }

    }
}
