package be.iccbxl.pid.youforbel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.youforbel.model.User;
import be.iccbxl.pid.youforbel.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User getUserByLogin(String login) {
        return repository.findByLogin(login);
    }

    public User save(User user) {
        return repository.save(user);
    }
}