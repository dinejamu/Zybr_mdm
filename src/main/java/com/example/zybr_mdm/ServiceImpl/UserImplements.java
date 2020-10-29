package com.example.zybr_mdm.ServiceImpl;

import com.example.zybr_mdm.Model.Employee;
import com.example.zybr_mdm.Repositories.UserRepo;
import com.example.zybr_mdm.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImplements implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public Employee registor(Employee employee) {
        return userRepo.save(employee);
    }

    @Override
    public Employee findemail(String email) {
        return userRepo.findByEmail(email);
    }

    /*@Override
    public Employee UserLogin(String emailid, String password) {
        return null;
    }*/
}
