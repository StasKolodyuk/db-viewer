package by.bsu.kolodyuk.repository;


import by.bsu.kolodyuk.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLoginAndPassword(String login, String password);

}
