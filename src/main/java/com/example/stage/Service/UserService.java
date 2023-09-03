package com.example.stage.Service;

import com.example.stage.Models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface UserService {
   Optional <User> getUserByEmail(String email);
   public List<User> getAllManager();
   List<User> getAllEmployees();
   void deleteUser(Long userId);
   public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user);
   User getUserById(Long userId);
}
