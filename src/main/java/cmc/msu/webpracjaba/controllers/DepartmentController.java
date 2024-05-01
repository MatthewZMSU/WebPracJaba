package cmc.msu.webpracjaba.controllers;

import cmc.msu.webpracjaba.DAO.DepartmentDAO;
import cmc.msu.webpracjaba.DAO.EmployeeInfoDAO;
import cmc.msu.webpracjaba.models.Department;
import cmc.msu.webpracjaba.models.Employee;
import cmc.msu.webpracjaba.models.EmployeeInfo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DepartmentController {
    @Getter
    class DepartmentWrapper {
        private Department department;
        private List<Department> subDepartments;
        private Map<String, List<Employee>> job2Employees;
        private int employeeCount;

        public DepartmentWrapper(Department department) {
            this.department = department;

            this.subDepartments = new ArrayList<>();
            for (Department subDepartment : departmentDAO.getAll()) {
                final Department headDepartment = subDepartment.getHead_department();
                if (headDepartment != null && headDepartment.getDepartment_id().equals(department.getDepartment_id())) {
                    subDepartments.add(subDepartment);
                }
            }

            this.employeeCount = 0;
            this.job2Employees = new HashMap<>();
            for (EmployeeInfo employeeInfo : employeeInfoDAO.getAll()) {
                if (employeeInfo.getEnd_date() == null &&
                        employeeInfo.getDepartment_id().getDepartment_id().equals(department.getDepartment_id())) {
                    List<Employee> employees = this.job2Employees.get(employeeInfo.getJob_post_id().getName());
                    if (employees == null) {
                        employees = new ArrayList<>();
                    }
                    employees.add(employeeInfo.getEmployee_id());
                    this.job2Employees.put(employeeInfo.getJob_post_id().getName(), employees);
                    this.employeeCount += 1;
                }
            }
        }
    }
    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private EmployeeInfoDAO employeeInfoDAO;

    @GetMapping("/department/{departmentId}")
    public String showDepartment(@PathVariable int departmentId,
                                 Model model) {
        model.addAttribute("departmentId", departmentId);
        Department department = departmentDAO.getById(departmentId);
        DepartmentWrapper finalDepartment = new DepartmentWrapper(department);
        model.addAttribute("department", finalDepartment);
        return "department";
    }

    @GetMapping("/department")
    public String getDepartments(Model model) {
        return "department_search";
    }

    @GetMapping("/department/search")
    public String searchDepartment(
            @RequestParam(required = false) String departmentName,
            @RequestParam(required = false) String directorLastName,
            Model model) {
        List<DepartmentWrapper> finalDepartments = new ArrayList<>();

        if (departmentName != null && departmentName.isEmpty()) departmentName = null;
        if (directorLastName != null && directorLastName.isEmpty()) directorLastName = null;

//        DepartmentDAO.getFilterBuilder()
//                .

        return "department_search";
    }
}
