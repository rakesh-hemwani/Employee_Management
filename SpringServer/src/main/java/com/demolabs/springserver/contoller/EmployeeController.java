package com.demolabs.springserver.contoller;

import com.demolabs.springserver.model.Employee;
import com.demolabs.springserver.service.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @GetMapping("/get-all")
    public List<Employee> getAllEmployees(){
        return employeeDao.getAllEmployees();
    }

    @PostMapping("/add-employee")
    public ResponseEntity<String> registerEmployee(@RequestBody Employee employee){
        boolean isAdded=employeeDao.saveEmployee(employee)!=null?true:false;
        if(isAdded){
            return new ResponseEntity<>("Employee Added Successfully.", HttpStatus.CREATED);
        }
        else return new ResponseEntity<>("Failed... Check Your Payload",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("save")
    public Employee save(@RequestBody Employee employee) {
        return employeeDao.saveEmployee(employee);
    }

    @PostMapping("/register-employee")
    public Employee registerEmp(@RequestBody Employee employee){
        return employeeDao.saveEmployee(employee);
    }
}
