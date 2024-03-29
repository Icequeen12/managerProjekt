package com.example.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<WorkersUpload> uploads;
    private List<String> idList;
    private ListView listViewWorkers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listViewWorkers = (ListView) findViewById(R.id.list);
        uploads = new ArrayList<>();
        idList = new ArrayList<>();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("workers");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                uploads.clear();

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    WorkersUpload upload = postSnapshot.getValue(WorkersUpload.class);
                    uploads.add(upload);
                    idList.add(postSnapshot.getKey());
                }

                task();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void task() {
        WorkersList workerAdapter = new WorkersList(ListActivity.this, uploads);
        listViewWorkers.setAdapter(workerAdapter);

        listViewWorkers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, OneWorkerActivity.class);
                intent.putExtra("name", uploads.get(position).getName());
                intent.putExtra("lastName", uploads.get(position).getLastName());
                intent.putExtra("phone", uploads.get(position).getPhone());
                intent.putExtra("email", uploads.get(position).getEmail());
                intent.putExtra("id", idList.get(position));
                startActivity(intent);
            }
        });
    }

    public void addWorker(View view) {
        Intent intent = new Intent(ListActivity.this, NewWorker.class);
        startActivity(intent);
    }
}
