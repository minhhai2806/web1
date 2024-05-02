package haihien.identityservice.controller;

import haihien.identityservice.models.User;
import haihien.identityservice.repository.UserRepository;
import haihien.identityservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {

        User savedUser=userService.registerUser(user);

        return savedUser;
    }

    @GetMapping("/users")
    public List<User> getUsers() {

        List<User> users = userRepository.findAll();


        return users;
    }


    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id )throws Exception {

        User user = userService.findUserById(id);
        return user;
    }



    @PutMapping("/users/{userId}")
    public User updatedUser(@RequestBody User user, @PathVariable Integer userId) throws Exception {
        User updatedUser = userService.updateUser(user, userId);
        return updatedUser;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) throws Exception {

        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new Exception("user not exist with id" + userId);
        }
        userRepository.delete(user.get());
        return "user deleted successfully with id "+userId;
    }
    @GetMapping("/users/search")
    public List<User> searchUser(@RequestParam("query")String query){
        List<User> users=userService.searchUser(query);
        return users;
    }

}
