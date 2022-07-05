package mini.proj.controller;

import mini.proj.exception.RecordNotFoundException;
import mini.proj.model.Employee;
import mini.proj.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity <Page<Employee>> getAllEmployees(Pageable pageable) {
        return new ResponseEntity<>(employeeService.getAllEmployees(pageable),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity <Employee> createEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.createEmployee(employee),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deleteEmployee(@PathVariable Long id) throws RecordNotFoundException {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity <Employee> getEmployeeById(@PathVariable Long id) throws RecordNotFoundException {
        return new ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity <Employee> updateEmployee(@PathVariable Long id,@RequestBody  Employee employee) throws RecordNotFoundException {
        return new ResponseEntity<>(employeeService.updateEmployee(employee, id),HttpStatus.OK);
    }
}
