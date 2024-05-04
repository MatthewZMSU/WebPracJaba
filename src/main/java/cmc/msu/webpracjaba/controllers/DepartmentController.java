package cmc.msu.webpracjaba.controllers;

import cmc.msu.webpracjaba.DAO.DepartmentDAO;
import cmc.msu.webpracjaba.DAO.EmployeeDAO;
import cmc.msu.webpracjaba.DAO.EmployeeInfoDAO;
import cmc.msu.webpracjaba.models.Department;
import cmc.msu.webpracjaba.models.Employee;
import cmc.msu.webpracjaba.models.EmployeeInfo;
import javassist.tools.reflect.CannotCreateException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    private EmployeeDAO employeeDAO;

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
        if (departmentName != null && departmentName.isEmpty()) departmentName = null;
        if (directorLastName != null && directorLastName.isEmpty()) directorLastName = null;

        DepartmentDAO.Filter departmentFilter = DepartmentDAO.getFilterBuilder()
                .directorLastName(directorLastName)
                .name(departmentName)
                .build();
        List<Department> departments = departmentDAO.searchDepartment(departmentFilter);
        model.addAttribute("departments", departments);
        return "department_search";
    }

    @PostMapping("/department/edit")
    public String editDepartment(
            @RequestParam() Integer departmentID,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Integer directorID,
            @RequestParam(required = false) Integer headDepartmentID,
            Model model
    ) {
        model.addAttribute("departmentID", departmentID);

        Employee newDirector = directorID == null ? null : employeeDAO.getById(directorID);
        if (directorID != null && newDirector == null) throw new IllegalArgumentException("Incorrect director ID!");
        Department newHeadDepartment = headDepartmentID == null ? null : departmentDAO.getById(headDepartmentID);
        if (headDepartmentID != null && newHeadDepartment == null) throw new IllegalArgumentException("Incorrect director ID!");

        Department oldDepartment = departmentDAO.getById(departmentID);
        if (name != null && !name.isEmpty()) oldDepartment.setName(name);
        if (description != null && !description.isEmpty()) oldDepartment.setDescription(description);
        if (directorID != null) oldDepartment.setDirector(newDirector);
        if (headDepartmentID != null) oldDepartment.setHead_department(newHeadDepartment);
        if (departmentDAO.update(oldDepartment) == null) throw new RuntimeException("Could not update department!");

        return "redirect:/department/" + departmentID;
    }

    @GetMapping("/department/add")
    public String addDepartment(Model model) {
        return "department_add";
    }

    @PostMapping("/department/add")
    public String addDepartment(
            @RequestParam() String name,
            @RequestParam() String description,
            @RequestParam() Integer directorID,
            @RequestParam(required = false) Integer headDepartmentID,
            Model model
    ) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Incorrect department name!");
        if (description == null || description.isEmpty()) throw new IllegalArgumentException("Incorrect description!");

        Employee director = employeeDAO.getById(directorID);
        if (director == null) throw new IllegalArgumentException("Incorrect director ID!");
        Department headDepartment = headDepartmentID == null ? null : departmentDAO.getById(headDepartmentID);
        if (headDepartmentID != null && headDepartment == null) throw new IllegalArgumentException("Incorrect head department ID!");

        Department newDepartment = new Department(
                0,
                name,
                description,
                director,
                headDepartment
        );
        newDepartment = departmentDAO.save(newDepartment);
        if (newDepartment == null) throw new RuntimeException("Cannot create new department!");

        model.addAttribute("departmentID", newDepartment.getId());
        return "department_add";
    }

    @PostMapping("/department/delete")
    public String deleteEmployee(
            @RequestParam(required = false) Integer departmentID,
            Model model
    ) {
        departmentDAO.deleteById(departmentID);
        return "department_search";
    }
}
