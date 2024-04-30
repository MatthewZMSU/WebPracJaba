package cmc.msu.webpracjaba.controllers;

import cmc.msu.webpracjaba.DAO.DepartmentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentDAO departmentDAO;

    @GetMapping
    public String getDepartment(Model model) {
        return "department";
    }
}
