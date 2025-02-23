package com.example.spa.service;
import java.util.ArrayList;
import com.example.spa.modal.Employee;
public interface EmpService {
    ArrayList<Employee> findAllEmployee();
    Employee findAllEmployeeByID(long id);
    void addEmployee();
    void deleteAllData();
}
