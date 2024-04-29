package cmc.msu.webpracjaba.controllers;

import cmc.msu.webpracjaba.DAO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/"})
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
    public String index() {
        return "Hello World";
    }
}
