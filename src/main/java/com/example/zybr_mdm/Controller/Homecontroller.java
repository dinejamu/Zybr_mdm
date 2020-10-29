package com.example.zybr_mdm.Controller;

import com.example.zybr_mdm.Model.Employee;
import com.example.zybr_mdm.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Homecontroller {

    @Autowired
    UserService userService;
   @Autowired
   BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/")
    public String home(){
        return "signup";
    }

    @RequestMapping("/login")
    public String loginpage(){
        return "login";
    }

    /*@RequestMapping("/login1")
    public String homepage(){
        return "welcome";
    }*/

   /* @RequestMapping("/login")
    public String login(){
        return "login";
    }*/

    @RequestMapping("registor")
    public ModelAndView registor(Employee employee, BindingResult bindingResult){
        /*if(bindingResult.hasErrors()){
            bindingResult.rejectValue("error","error.user","Try Again.... ");
            return new ModelAndView("error");
        }*/
        ModelAndView mv = new ModelAndView();
        Employee obj=userService.findemail(employee.getEmail());
        if(obj!=null){
            mv.setViewName("/signup");
            mv.addObject("error"," This Email id is already Registor..");
            return mv;
        }
        else{

        String pass=employee.getPassword();
        String enCryptPassword = bCryptPasswordEncoder.encode(pass);
        employee.setPassword(enCryptPassword);
        userService.registor(employee);
        mv.setViewName("/signup");
        mv.addObject("success"," Registration successfully completed click to Signin..");
        return mv;
        }
    }
    @RequestMapping(value = "login1",method = RequestMethod.POST)
    public ModelAndView Userlogin(@RequestParam("email") String emailid, @RequestParam("password") String Password) {
        ModelAndView mv = new ModelAndView();
        try {
            Employee employee = userService.findemail(emailid);
            //String enCrepassword=bCryptPasswordEncoder.encode(Password);
            String pass=employee.getPassword();
            //System.out.println(pass+"||"+Password);
            if (bCryptPasswordEncoder.matches(Password,pass)) {
                mv.setViewName("welcome");
                //mv.addObject(employee);
            } else {
                mv.setViewName("login");
                mv.addObject("invalid", " Email id & Password incorrect..");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            mv.setViewName("login");
            mv.addObject("invalid", " This Email id does not exists..");
        }
        return mv;
    }

    /*@RequestMapping(value = "forgetpassword")
    public void forgetpassword(){

    }*/
}
