package WarehouseAPI.WarehouseAPI.service;

import WarehouseAPI.WarehouseAPI.entity.Role;
import WarehouseAPI.WarehouseAPI.entity.User;
import WarehouseAPI.WarehouseAPI.repository.RoleRepository;
import WarehouseAPI.WarehouseAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }


    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public User setPasswordAndDefaultRoleAndGet(User user) {
        String defaultRole = "ROLE_USER";
        user.setRoles(Collections.singleton(new Role(1L, defaultRole)));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return user;
    }

    public boolean saveUser(User user) {
        if (isExistsUser(user)) {
            return false;
        }
        user = setPasswordAndDefaultRoleAndGet(user);
        userRepository.save(user);
        return true;
    }

    public boolean isExistsUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        return userFromDB != null;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> getUserList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class).setParameter("paramId", idMin).getResultList();
    }

    public void setRole(Long userId, Long roleId) {
        Role role = roleRepository.getById(roleId);
        User user = findUserById(userId);
        user.getRoles().add(role);
        userRepository.save(user);
    }

    public List<Role> allRoles() {
        return roleRepository.findAll();
    }
}