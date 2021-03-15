package CrudDemo.service.impl;

import CrudDemo.model.Employee;
import CrudDemo.repo.IEmployeeRepository;
import CrudDemo.service.IEmployeeService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Singleton
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {

    @Inject
    private IEmployeeRepository employeeRepository;

    @Override
    public Employee getEmployee(Long id) {

        Employee employee = null;
        try {
            employee = employeeRepository.findById(id).orElseThrow(Exception::new);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public void saveEmployee(Employee employee) {

        Employee employee1 = new Employee();
        employee1.setName(employee.getName());
        employeeRepository.save(employee1);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        
        boolean isEmployeeValid = employeeRepository.existsById(id);
        
        if(isEmployeeValid)
            employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Long id, String name) {
        
        Employee employee = null;

        Optional<Employee> employee1 = employeeRepository.findById(id);
        if(employee1.isPresent()) {
            employee = employee1.get();
            
            employee.setName(name);
            employee = employeeRepository.save(employee);
        }
        
        return employee;
    }
}
