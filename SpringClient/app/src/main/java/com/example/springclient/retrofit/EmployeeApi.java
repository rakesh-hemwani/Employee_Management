package com.example.springclient.retrofit;

import com.example.springclient.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EmployeeApi {

    @GET("/employee/get-all")
    Call<List<Employee>> getAllEmployess();

    @POST("/employee/save")
    Call<Employee> save(@Body Employee employee);
}
