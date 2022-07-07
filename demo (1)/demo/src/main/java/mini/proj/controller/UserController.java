package mini.proj.controller;

import mini.proj.exception.RecordNotFoundException;
import mini.proj.model.User;
import mini.proj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        return new ResponseEntity<>(userService.getAllUsers(pageable), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity <User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity <String> deleteUser(@PathVariable Long id) throws RecordNotFoundException {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity <User> getUserById(@PathVariable Long id) throws RecordNotFoundException {
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity <User> updateUser(@PathVariable Long id,@RequestBody  User user) throws RecordNotFoundException {
        return new ResponseEntity<>(userService.updateUser(user, id),HttpStatus.OK);
    }

}
