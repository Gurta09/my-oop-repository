package mini.proj.service;

import antlr.collections.List;
import mini.proj.exception.RecordNotFoundException;
import mini.proj.model.Employee;
import mini.proj.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) throws RecordNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        employee.ifPresent(value -> employeeRepository.delete(value));
        throw new RecordNotFoundException("Employee not found!");
    }

    @Override
    public Employee getEmployeeById(Long id) throws RecordNotFoundException {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Employee not found!"));
    }

    @Override
    public Employee updateEmployee(Employee reqEmployee, Long id) throws RecordNotFoundException {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(reqEmployee.getName());
            employee.setLevel(reqEmployee.getLevel());
            employee.setStatus(reqEmployee.getStatus());
            return employeeRepository.save(employee);
        }).orElseThrow(() -> new RecordNotFoundException("id " + id + "Employee not found!"));
    }

    @Override
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

}
