package com.contractormanagemet.Contractor.Magement.Service;



import com.contractormanagemet.Contractor.Magement.DTO.ProjectDto.ProjectResponseDto;
import com.contractormanagemet.Contractor.Magement.DTO.SuperisorDto.RequestSuperisor;
import com.contractormanagemet.Contractor.Magement.Entity.*;
import com.contractormanagemet.Contractor.Magement.Repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private AdminRepository adminDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SuperisorRepository superisorRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SubAdminRepository subAdminRepository;

    @Autowired
    private ModelMapper modelMapper; // To map entities to DTOs

    public void initRoleAndUser() {
        // Create roles
        if (!roleDao.existsById("Admin")) {
            Role adminRole = new Role();
            adminRole.setRoleName("Admin");
            adminRole.setRoleDescription("Admin role");
            roleDao.save(adminRole);
        }
        // Create roles
        if (!roleDao.existsById("Supervisor")) {
            Role adminRole = new Role();
            adminRole.setRoleName("Supervisor");
            adminRole.setRoleDescription("Supervisor role");
            roleDao.save(adminRole);
        }
        if(!roleDao.existsById("Employee")){
            Role employeeRole=new Role();
            employeeRole.setRoleName("Employee");
            employeeRole.setRoleDescription("Employee role");
            roleDao.save(employeeRole);
        }
        if(!roleDao.existsById("SubAdmin")){
            Role subAdminRole=new Role();
            subAdminRole.setRoleName("SubAdmin");
            subAdminRole.setRoleDescription("SubAdmin role");
            roleDao.save(subAdminRole);
        }
    }

    public Admin registerAdmin(Admin admin) {
        Role role = roleDao.findById("Admin").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        admin.setRole(userRoles);
        admin.setPassword(getEncodedPassword(admin.getPassword()));

        adminDao.save(admin);
        return admin;
    }

    public Employee registerEmployee(Employee employee) {
        Role role=roleDao.findById("Employee").get();
        Set<Role> empRole=new HashSet<>();
        empRole.add(role);
        employee.setRole(empRole);
        employee.setPassword(getEncodedPassword(employee.getPassword()));
        employeeRepository.save(employee);
        return employee;

    }
    public SubAdmin registerSubAdmin(SubAdmin subAdmin) {
        Role role=roleDao.findById("SubAdmin").get();
        Set<Role> subAdminRole=new HashSet<>();
        subAdminRole.add(role);
        subAdmin.setRole(subAdminRole);
        subAdmin.setPassword(getEncodedPassword(subAdmin.getPassword()));
        subAdminRepository.save(subAdmin);
        return subAdmin;
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

//    public List<ProjectResponseDto> getAllowedSitesForUser(String email) {
//       Superisor superisor=superisorRepository.findByEmail(email);
//
//        return superisor.getAllowedSite().stream()
//                .map(site-> modelMapper.map(site, ProjectResponseDto.class))
//                        .collect(Collectors.toList());
//    }

    public List<ProjectResponseDto> getAllowedSitesForUser(String email) {
        Superisor superisor = superisorRepository.findByEmail(email);

        if (superisor == null) {
            throw new RuntimeException("Supervisor with email " + email + " not found");
        }

        return superisor.getAllowedSite().stream()
                .map(site -> modelMapper.map(site, ProjectResponseDto.class))
                .collect(Collectors.toList());

    }

    public Superisor registerSuperisor(RequestSuperisor dto) {
        Role role = roleDao.findById("Supervisor")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Superisor superisor = new Superisor();
        superisor.setName(dto.getName());
        superisor.setEmail(dto.getEmail());
        superisor.setPassword(getEncodedPassword(dto.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        superisor.setRole(roles);

        return superisorRepository.save(superisor);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public List<SubAdmin> getAllSubAdmin() {
        return subAdminRepository.findAll();
    }
}