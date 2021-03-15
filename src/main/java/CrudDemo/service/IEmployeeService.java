package CrudDemo.service;

import CrudDemo.model.Employee;

import java.util.List;

public interface IEmployeeService {


    Employee getEmployee(Long id);

    void saveEmployee(Employee employee);

    void deleteEmployeeById(Long id);

    List<Employee> getAllEmployees();

    Employee updateEmployee(Long id, String employeeName);

}
