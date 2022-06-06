package mini.proj.service;

import mini.proj.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    void deleteEmployee(Long id);

    List<Employee> getAllEmployee();

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Employee employee, Long id);

}
