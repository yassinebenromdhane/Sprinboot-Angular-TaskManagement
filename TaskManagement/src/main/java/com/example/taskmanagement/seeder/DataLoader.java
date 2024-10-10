//package com.example.taskmanagement.seeder;
//
//import com.example.taskmanagement.entities.Privilege;
//import com.example.taskmanagement.entities.Role;
//import com.example.taskmanagement.repositories.PrivilegeRepository;
//import com.example.taskmanagement.repositories.RoleRepository;
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class DataLoader {
//
//    private final RoleRepository roleRepository;
//    private final PrivilegeRepository privilegeRepository;
//
//    public DataLoader(RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
//        this.roleRepository = roleRepository;
//        this.privilegeRepository = privilegeRepository;
//    }
//
//    @PostConstruct
//    @Transactional
//    public void loadData() {
//        // Create Privileges
//        Privilege createUser = createPrivilegeIfNotFound("create_user");
//        Privilege deleteUser = createPrivilegeIfNotFound("delete_user");
//        Privilege updateUser = createPrivilegeIfNotFound("update_user");
//        Privilege getUser = createPrivilegeIfNotFound("get_user");
//        Privilege createTask = createPrivilegeIfNotFound("create_task");
//        Privilege updateTask = createPrivilegeIfNotFound("update_task");
//        Privilege deleteTask = createPrivilegeIfNotFound("delete_task");
//        Privilege getTask = createPrivilegeIfNotFound("get_task");
//
//        // Create sets of privileges for Admin and User
//        Set<Privilege> adminPrivileges = new HashSet<>();
//        adminPrivileges.add(createUser);
//        adminPrivileges.add(deleteUser);
//        adminPrivileges.add(updateUser);
//        adminPrivileges.add(getUser);
//        adminPrivileges.add(createTask);
//        adminPrivileges.add(updateTask);
//        adminPrivileges.add(deleteTask);
//        adminPrivileges.add(getTask);
//
//        Set<Privilege> userPrivileges = new HashSet<>();
//        userPrivileges.add(createTask);
//        userPrivileges.add(updateTask);
//        userPrivileges.add(deleteTask);
//        userPrivileges.add(getTask);
//
//        // Create Roles
//        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
//        createRoleIfNotFound("ROLE_USER", userPrivileges);
//    }
//
//    @Transactional
//    protected Privilege createPrivilegeIfNotFound(String name) {
//        Privilege privilege = privilegeRepository.findByName(name);
//        if (privilege == null) {
//            privilege = new Privilege();
//            privilege.setName(name);
//            privilege = privilegeRepository.save(privilege);
//        }
//        return privilege;
//    }
//
//    @Transactional
//    protected Role createRoleIfNotFound(String name, Set<Privilege> privileges) {
//        Role role = roleRepository.findByName(name);
//        if (role == null) {
//            role = new Role();
//            role.setName(name);
//            role.setPrivileges(privileges);
//            role = roleRepository.save(role);
//        }
//        return role;
//    }
//}