package be.iccbxl.pid.youforbel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import be.iccbxl.pid.youforbel.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

    List<User> findByLastname(String lastname);

    User findById(long id);
}
