package com.example.stage.Controller;

import com.example.stage.Models.User;
import com.example.stage.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gestion")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



  //  @GetMapping("/users/{email}")
   // public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
    //    Optional<User> userOptional = userService.getUserByEmail(email);
   //     if (userOptional.isPresent()) {
    //        User user = userOptional.get();
   //         return ResponseEntity.ok(user);
  //      } else {
     //       return ResponseEntity.notFound().build();
   //     }
 //   }

    @GetMapping("/user/email/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        return Optional.ofNullable(userService.getUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found")));
    }


    @GetMapping("/user/getAll/employee")
    public List<User> getAllEmployees() {
        return userService.getAllEmployees();
    }

    @GetMapping("/user/getAll/manager")
    public List<User> getAllManager() {
        return userService.getAllManager();
    }


    @DeleteMapping("/delete/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/update/user/{id}")
    public ResponseEntity<User> updateCollaborateur(@PathVariable Long id,@RequestBody User user) {
        return userService.updateUser(id,user);
    }

    @GetMapping("/find/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
