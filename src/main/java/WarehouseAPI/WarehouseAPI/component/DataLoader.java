package WarehouseAPI.WarehouseAPI.component;


import WarehouseAPI.WarehouseAPI.entity.Role;
import WarehouseAPI.WarehouseAPI.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    private RoleRepository roleRepository;
    @Autowired

    public DataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        LoadRoles();
    }

    private void  LoadRoles(){
       roleRepository.save(new Role(1L,"ROLE_USER"));
       roleRepository.save(new Role(2L,"ROLE_ADMIN"));
    }
}
