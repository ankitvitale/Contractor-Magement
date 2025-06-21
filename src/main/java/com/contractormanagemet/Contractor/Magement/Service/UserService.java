package com.contractormanagemet.Contractor.Magement.Service;



import com.contractormanagemet.Contractor.Magement.DTO.ProjectDto.ProjectResponseDto;
import com.contractormanagemet.Contractor.Magement.DTO.SuperisorDto.RequestSuperisor;
import com.contractormanagemet.Contractor.Magement.Entity.Admin;
import com.contractormanagemet.Contractor.Magement.Entity.Role;
import com.contractormanagemet.Contractor.Magement.Entity.Superisor;
import com.contractormanagemet.Contractor.Magement.Repository.AdminRepository;
import com.contractormanagemet.Contractor.Magement.Repository.RoleDao;
import com.contractormanagemet.Contractor.Magement.Repository.SuperisorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public List<ProjectResponseDto> getAllowedSitesForUser(String email) {
       Superisor superisor=superisorRepository.findByEmail(email);

        return superisor.getAllowedSite().stream()
                .map(site-> modelMapper.map(site, ProjectResponseDto.class))
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


}