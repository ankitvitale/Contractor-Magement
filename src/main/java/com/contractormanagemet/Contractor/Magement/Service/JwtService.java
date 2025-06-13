package com.contractormanagemet.Contractor.Magement.Service;


import com.contractormanagemet.Contractor.Magement.Entity.Admin;
import com.contractormanagemet.Contractor.Magement.Entity.JwtRequest;
import com.contractormanagemet.Contractor.Magement.Entity.JwtResponse;
import com.contractormanagemet.Contractor.Magement.Entity.Superisor;
import com.contractormanagemet.Contractor.Magement.Repository.AdminRepository;
import com.contractormanagemet.Contractor.Magement.Repository.SuperisorRepository;
import com.contractormanagemet.Contractor.Magement.Security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtHelper jwtUtil;

    @Autowired
    private AdminRepository adminDao;

    @Autowired
    private SuperisorRepository supervisorDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String email = jwtRequest.getEmail();
        String password = jwtRequest.getPassword();

        // Authenticate the user
        authenticate(email, password);

        // Load user details and generate JWT token
        UserDetails userDetails = loadUserByUsername(email);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        // Check if the email belongs to Admin or Supervisor
        Admin admin = adminDao.findByEmail(email);
        Superisor supervisor = supervisorDao.findByEmail(email);

        if (admin != null) {
            return new JwtResponse(admin, null, newGeneratedToken);
        } else if (supervisor != null) {
            return new JwtResponse(null, supervisor, newGeneratedToken);
        } else {
            throw new Exception("User not found with email: " + email);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        Admin admin = adminDao.findByEmail(email);
        Superisor supervisor = supervisorDao.findByEmail(email);

        Collection<GrantedAuthority> authorities = new HashSet<>();
        String password = null;

        if (admin != null) {
            admin.getRole().forEach(role ->
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
            );
            password = admin.getPassword();
        }

        if (supervisor != null) {
            supervisor.getRole().forEach(role ->
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
            );
            password = supervisor.getPassword();
        }

        return new org.springframework.security.core.userdetails.User(email, password, authorities);
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

