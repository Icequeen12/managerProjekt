package com.example.manager;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class WorkersList extends ArrayAdapter<WorkersUpload> {

    private Activity context;
    private List<WorkersUpload> workersUploadList;


    public WorkersList(Activity context, List<WorkersUpload> workersUploadList) {
        super(context, R.layout.workers_list, workersUploadList);
        this.context = context;
        this.workersUploadList = workersUploadList;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.workers_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.txtName);

        WorkersUpload artist = workersUploadList.get(position);
        textViewName.setText(artist.getName() + " " + artist.getLastName());

        return listViewItem;
    }


}
