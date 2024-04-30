package cmc.msu.webpracjaba.controllers;

import cmc.msu.webpracjaba.DAO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "/home"})
public class IndexController {
    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private DutyDAO dutyDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private EmployeeInfoDAO employeeInfoDAO;

    @Autowired
    private JobPostDAO jobPostDAO;

    @Autowired
    private UserAccountDAO userAccountDAO;

    @Autowired
    private UserTypeDAO userTypeDAO;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ServerTime", 12345);
        return "home";
    }
}
