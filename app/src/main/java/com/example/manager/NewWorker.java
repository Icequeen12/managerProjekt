package com.example.manager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewWorker extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private EditText editName, editLastName, editPhone, editEmail;
    private Button btnAdd;
    private String name, lastName, phone, email, id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_worker);

        editName = (EditText)findViewById(R.id.editName);
        editLastName = (EditText)findViewById(R.id.editLastName);
        editPhone = (EditText)findViewById(R.id.editPhone);
        editEmail = (EditText)findViewById(R.id.editEmail);
        btnAdd = (Button)findViewById(R.id.btnAdd);
    }


    public void add(View view){
        name = editName.getText().toString().trim();
        lastName = editLastName.getText().toString().trim();
        phone = editPhone.getText().toString().trim();
        email = editEmail.getText().toString().trim();

        if(name.isEmpty() || lastName.isEmpty() || phone.isEmpty() || email.isEmpty()){
            Toast.makeText(NewWorker.this, "Uzupełnij dane", Toast.LENGTH_LONG).show();
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(NewWorker.this, "Podaj poprawny email", Toast.LENGTH_LONG).show();
        }else if (phone.length()!=9){
            Toast.makeText(NewWorker.this, "Podaj poprawny numer", Toast.LENGTH_LONG).show();
        }
        else {
            saveData();
        }
    }


    public void saveData(){

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("workers");

        if (TextUtils.isEmpty(id)) {
            id = mFirebaseDatabase.push().getKey();
        }

        WorkersUpload result = new WorkersUpload(name,lastName,phone,email);
        mFirebaseDatabase.child(id).setValue(result);

        finish();
    }
}
