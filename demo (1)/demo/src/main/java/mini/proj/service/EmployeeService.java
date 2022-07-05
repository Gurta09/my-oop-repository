package mini.proj.service;

import mini.proj.exception.RecordNotFoundException;
import mini.proj.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    void deleteEmployee(Long id) throws RecordNotFoundException;

    Employee getEmployeeById(Long id) throws RecordNotFoundException;

    Employee updateEmployee(Employee employee, Long id) throws RecordNotFoundException;

    Page<Employee> getAllEmployees(Pageable pageable);
}
