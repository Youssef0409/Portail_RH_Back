package com.example.stage.Service.impl;

import com.example.stage.Dao.UserRepository;
import com.example.stage.Models.Role;
import com.example.stage.Models.User;
import com.example.stage.Service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequestMapping("/gestion")
public class UserServiceImpl implements UserService {

    private  final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllEmployees() {
        return userRepository.findByRole(Role.Employee);
    }

    public List<User> getAllManager() {
        return userRepository.findByRole(Role.Manager);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


    @Override
    @Transactional
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @RequestBody User user) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        User existingUser = optionalUser.get();
        Optional<User> existingUserOptional = userRepository.findByEmail(user.getEmail());
        if (existingUserOptional.isPresent()) {

                if (!existingUser.getId().equals(existingUser.getId())) {
                return ResponseEntity.badRequest().body(null);
            }
        }


        existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());
        existingUser.setDateNaissance(user.getDateNaissance());
        existingUser.setEmail(user.getEmail());
        if (user.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        existingUser.setTelephone(user.getTelephone());


        User updatedUser = userRepository.save(existingUser);

        return ResponseEntity.ok(updatedUser);
    }


    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElse(null); // You can handle the case when the user is not found differently
    }

}
