package mini.proj.service;

import mini.proj.enums.UserRole;
import mini.proj.exception.RecordNotFoundException;
import mini.proj.model.User;
import mini.proj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public String deleteUser(Long id) throws RecordNotFoundException {
        return repository.findById(id).map(user ->{
            repository.delete(user);
            return String.format("%s has been deleted!", user.getName());
        }).orElseThrow(() -> new RecordNotFoundException("User not found!"));
    }
    @Override
    public User getUserById(Long id) throws RecordNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User not found!"));
    }
    @Override
    public User updateUser(User reqUser, Long id) throws RecordNotFoundException {
        return repository.findById(id).map(user -> {
            user.setName(reqUser.getName());
            user.setPassword(reqUser.getPassword());
            user.setStatus(reqUser.getStatus());
            user.setRole(reqUser.getRole());
            return repository.save(user);
        }).orElseThrow(() -> new RecordNotFoundException("id " + id + "Employee not found!"));
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) { return repository.findAll(pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = repository.findByName(userId);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User( user.getName(), user.getPassword(), getAuthority(user));
    }
    private List<SimpleGrantedAuthority> getAuthority(User user) {
        UserRole userRole = Arrays.stream(UserRole.values()).filter(role -> {
            return role.name().equalsIgnoreCase(user.getRole().name());
        }).findFirst().orElse(null);

        return Arrays.asList(new SimpleGrantedAuthority(String.format("ROLE_%s", userRole.name())));
    }
    @Override
    public User saveUser(User user) {
        Optional.ofNullable(user.getPassword()).ifPresent(password -> {
            user.setPassword(new BCryptPasswordEncoder().encode(password));
        });
        return repository.save(user);
    }
}