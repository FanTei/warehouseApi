package WarehouseAPI.WarehouseAPI.repository;

import WarehouseAPI.WarehouseAPI.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {

}
