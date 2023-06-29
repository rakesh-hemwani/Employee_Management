package com.example.springclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.springclient.model.Employee;
import com.example.springclient.retrofit.EmployeeApi;
import com.example.springclient.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText inputEditName=findViewById(R.id.form_name);
        TextInputEditText inputEditBranch=findViewById(R.id.form_branch);
        TextInputEditText inputEditLocation=findViewById(R.id.form_location);

        MaterialButton buttonSave=findViewById(R.id.form_save_button);
        RetrofitService retrofitService=new RetrofitService();
        EmployeeApi employeeApi=retrofitService.getRetrofit().create(EmployeeApi.class);

        buttonSave.setOnClickListener(view -> {
            String name=String.valueOf(inputEditName.getText());
            String branch=String.valueOf(inputEditBranch.getText());
            String location=String.valueOf(inputEditLocation.getText());

            Employee emp=new Employee();
            emp.setBranch(branch);
            emp.setName(name);
            emp.setLocation(location);

            employeeApi.save(emp).enqueue(new Callback<Employee>() {
                @Override
                public void onResponse(Call<Employee> call, Response<Employee> response) {
                    Toast.makeText(EmployeeForm.this,"Save Done",Toast.LENGTH_SHORT).show();
//                    Intent inent=new Intent(EmployeeForm.this,EmployeeListActivity.class);
//                    inent.putExtra("fromEmployeeForm",true);
//                    startActivity(inent);
                    finish();
                }

                @Override
                public void onFailure(Call<Employee> call, Throwable t) {
                    Toast.makeText(EmployeeForm.this,"Save Failed, Ask to check the server",Toast.LENGTH_SHORT).show();
                   Logger.getLogger(EmployeeForm.class.getName()).log(Level.SEVERE,"Error Occurred",t);
                }
            });
        });
    }
}