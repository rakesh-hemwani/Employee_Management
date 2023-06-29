package com.example.springclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.springclient.adapter.EmployeeAdapter;
import com.example.springclient.model.Employee;
import com.example.springclient.retrofit.EmployeeApi;
import com.example.springclient.retrofit.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        recyclerView=findViewById(R.id.employeeList_recyclerView);
        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        FloatingActionButton floatingActionButton=findViewById(R.id.employeeAdd_fab);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent=new Intent(this,EmployeeForm.class);
            startActivity(intent);
        });

//        if (getIntent().getBooleanExtra("fromEmployeeForm", false)) {
//            loadEmployee();
//        }
        loadEmployee();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadEmployee();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        loadEmployee();
//    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        loadEmployee();
//    }

    private void loadEmployee() {

        RetrofitService retrofitService=new RetrofitService();
        EmployeeApi employeeApi=retrofitService.getRetrofit().create(EmployeeApi.class);

        employeeApi.getAllEmployess().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                populateListView(response.body());
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(EmployeeListActivity.this,"Failed to load employees",Toast.LENGTH_SHORT).show();
                Logger.getLogger(EmployeeListActivity.class.getName()).log(Level.SEVERE,"Error Occurred",t);
            }
        });

    }

    private void populateListView(List<Employee> employeeList) {

        EmployeeAdapter employeeAdapter=new EmployeeAdapter(employeeList);
        recyclerView.setAdapter(employeeAdapter);
    }
}