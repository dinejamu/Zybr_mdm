package com.example.zybr_mdm.Repositories;

import com.example.zybr_mdm.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository <Employee,Integer>{
Employee findByEmail(String email);
//Employee findByPassword
}
