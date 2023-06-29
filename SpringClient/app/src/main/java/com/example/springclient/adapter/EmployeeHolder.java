package com.example.springclient.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.springclient.R;

public class EmployeeHolder extends RecyclerView.ViewHolder {

    TextView name, location, branch;
    public EmployeeHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.employeeList_Name);
        branch=itemView.findViewById(R.id.employeeList_branch);
        location=itemView.findViewById(R.id.employeeList_Location);
    }
}
