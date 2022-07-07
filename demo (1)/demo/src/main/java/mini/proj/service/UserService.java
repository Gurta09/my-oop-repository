package mini.proj.service;

import mini.proj.exception.RecordNotFoundException;
import mini.proj.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    String deleteUser(Long id) throws RecordNotFoundException;

    User updateUser(User reqUser, Long id) throws RecordNotFoundException;

    Page<User> getAllUsers(Pageable pageable);

    User getUserById(Long id) throws RecordNotFoundException;

    User saveUser(User user);
}
