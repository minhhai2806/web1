package haihien.identityservice.service;

import haihien.identityservice.models.User;

import java.util.List;

public interface UserService {
    public User registerUser(User user);
    public User findUserById(Integer userId) throws Exception;
    public User findUserByEmail(String email);
    public User updateUser(User user, Integer userId) throws Exception;
    public List<User> searchUser(String query);

}
