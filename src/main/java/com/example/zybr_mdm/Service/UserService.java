package com.example.zybr_mdm.Service;

import com.example.zybr_mdm.Model.Employee;

public interface UserService {
    Employee registor(Employee employee);
    Employee findemail(String email);

    /*Employee UserLogin(String emailid, String password);*/
}
