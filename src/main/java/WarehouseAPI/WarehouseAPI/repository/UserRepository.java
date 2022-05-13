package WarehouseAPI.WarehouseAPI.repository;

import WarehouseAPI.WarehouseAPI.entity.Role;
import WarehouseAPI.WarehouseAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
