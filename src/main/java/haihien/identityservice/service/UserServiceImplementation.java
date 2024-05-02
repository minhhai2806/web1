package haihien.identityservice.service;

import haihien.identityservice.models.User;
import haihien.identityservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {

            User newUser=new User();
            newUser.setId(user.getId());
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setDob(user.getDob());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            newUser.setJob(user.getJob());
            newUser.setLevel(user.getLevel());
            newUser.setDesire(user.getDesire());


            User savedUser=userRepository.save(newUser);

        return savedUser;
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new Exception("user not exist with userid" + userId);
    }

    @Override
    public User findUserByEmail(String email) {
        User user=userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User updateUser(User user, Integer userId) throws Exception {
        Optional<User> user1 = userRepository.findById(userId);
        if (user1.isEmpty()){
            throw new Exception("user not exist with id" + userId);
        }
        User oldUser = user1.get();
        if (user.getFirstName()!=null){
            oldUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName()!=null){
            oldUser.setLastName(user.getLastName());
        }
        if (user.getDob()!=null){
            oldUser.setDob(user.getDob());
        }
        if (user.getPassword()!=null){
            oldUser.setPassword(user.getPassword());
        }
        if (user.getLevel()!=null){
            oldUser.setLevel(user.getLevel());
        }
        if (user.getDesire()!=null){
            oldUser.setDesire(user.getDesire());
        }
        User updatedUser=userRepository.save(oldUser);

        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }


}
