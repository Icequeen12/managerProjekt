package com.example.manager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OneWorkerActivity extends AppCompatActivity {

    private String name, lastName, phone, email, id;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_worker);

        TextView txtName = (TextView) findViewById(R.id.txtName);
        TextView txtLastName = (TextView) findViewById(R.id.txtLastName);
        TextView txtPhone = (TextView) findViewById(R.id.txtPhone);
        TextView txtEmail = (TextView) findViewById(R.id.txtEmail);

        name = getIntent().getExtras().getString("name");
        lastName = getIntent().getExtras().getString("lastName");
        phone = getIntent().getExtras().getString("phone");
        email = getIntent().getExtras().getString("email");
        id = getIntent().getExtras().getString("id");

        txtName.setText(name);
        txtLastName.setText(lastName);
        txtPhone.setText(phone);
        txtEmail.setText(email);
    }

    public void onClickCall(View view) {
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        startActivity(i);
    }

    public void onClickEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Praca");
        startActivity(Intent.createChooser(intent, ""));
    }

    public void onClickEdit(View view) {
        Intent intent = new Intent(OneWorkerActivity.this, EditActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("lastName", lastName);
        intent.putExtra("phone", phone);
        intent.putExtra("email", email);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }

    public void onClickKick(View view) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.accept_box, null);
        dialogBuilder.setView(dialogView);

        alertDialog = dialogBuilder.create();
        alertDialog.show();

    }

    public void onClickYes(View view) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("workers").child(id);
        databaseReference.removeValue();
        finish();
    }

    public void onClickNope(View view) {
        alertDialog.cancel();
    }
}
