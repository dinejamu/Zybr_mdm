package com.example.zybr_mdm.Security;

import com.example.zybr_mdm.Model.Employee;
import com.example.zybr_mdm.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImplements implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Employee employee=userRepo.findByEmail(s);
        if(employee==null)
        throw new UsernameNotFoundException("Email id is not Found");
            return new UserService(employee);
    }
}
