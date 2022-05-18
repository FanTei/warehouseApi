package WarehouseAPI.WarehouseAPI.repository;

import WarehouseAPI.WarehouseAPI.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;


@DisplayName("Unit-level testing for UserRepository")
@ActiveProfiles("test")
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Sql("/sql/clearDatabase.sql")
    @Test
    public void shouldProperlySaveUser() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setPasswordConfirm(user.getPassword());
        userRepository.save(user);
        List<User> users = userRepository.findAll();
        User saved = userRepository.findByUsername(user.getUsername());
        Assertions.assertEquals(user, saved);
    }

    @Sql("/sql/users.sql")
    @Test
    public void shouldProperlyFindUserByUsername() {
        String username = "1";
        User find = userRepository.findByUsername(username);
        Assertions.assertEquals(find.getUsername(), "1");
    }
}
