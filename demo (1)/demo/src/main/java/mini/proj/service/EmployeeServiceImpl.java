package mini.proj.service;

import mini.proj.model.Employee;
import mini.proj.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        employee.ifPresent(value -> employeeRepository.delete(value));
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.orElse(null);
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            employeeOptional.get().setName(employee.getName());
            employeeOptional.get().setLevel(employee.getLevel());
            employeeOptional.get().setEmail(employee.getEmail());
            employeeOptional.get().setCommunity(employee.getCommunity());
            employeeOptional.get().setStatus(employee.getStatus());
            return employeeRepository.save(employeeOptional.get());
        }
    return null;
    }
}
