package cmc.msu.webpracjaba.controllers;

import cmc.msu.webpracjaba.DAO.EmployeeDAO;
import cmc.msu.webpracjaba.DAO.EmployeeInfoDAO;
import cmc.msu.webpracjaba.models.Department;
import cmc.msu.webpracjaba.models.Employee;
import cmc.msu.webpracjaba.models.EmployeeInfo;
import cmc.msu.webpracjaba.models.JobPost;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class EmployeeController {
    @Getter
    class EmployeeWrapper {
        private final Employee employee;
        private final int experience;
        private final Department department;
        private final String departmentName;
        private final String job;

        public EmployeeWrapper(Employee employee) {
            this.employee = employee;

            List<EmployeeInfo> employeeInfos = employeeInfoDAO.getEmployeeHistory(employee.getId());
            long employeeExperience = 0L;
            Department department = null;
            JobPost jobPost = null;

            for (EmployeeInfo employeeInfo : employeeInfos) {
                long endTime;
                if (employeeInfo.getEnd_date() == null) {
                    department = employeeInfo.getDepartment_id();
                    jobPost = employeeInfo.getJob_post_id();
                    endTime = Calendar.getInstance().getTimeInMillis();
                } else {
                    endTime = employeeInfo.getEnd_date().getTime();
                }
                employeeExperience += (endTime - employeeInfo.getStart_date().getTime()) / (1000 * 60 * 60 * 24);
            }

            this.experience = (int) (employeeExperience / 365);
            this.department = department;
            this.departmentName = department == null ? null : department.getName();
            this.job = jobPost == null ? null : jobPost.getName();
        }
    }

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private EmployeeInfoDAO employeeInfoDAO;

    @GetMapping("/employee")
    public String getEmployees(Model model) {
        return "employee_search";
    }

    @GetMapping("/employee/{employee_id}")
    public String getEmployee(@PathVariable int employee_id,
                              Model model) {
        model.addAttribute("employeeNo", employee_id);
        Employee employee = employeeDAO.getById(employee_id);
        EmployeeWrapper finalEmployee = new EmployeeWrapper(employee);
        model.addAttribute("employee", finalEmployee);
        return "employee";
    }

    @GetMapping("/employee/search")
    public String searchEmployee(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String middleName,
            @RequestParam(required = false) String departmentName,
            @RequestParam(required = false) String jobName,
            @RequestParam(required = false) Integer experience,
            Model model) {
        List<EmployeeWrapper> finalEmployees = new ArrayList<>();

        if (experience != null && experience < 0) {
            model.addAttribute("employees", finalEmployees);
            return "employee_search";
        }

        if (firstName != null && firstName.isEmpty()) firstName = null;
        if (lastName != null && lastName.isEmpty()) lastName = null;
        if (middleName != null && middleName.isEmpty()) middleName = null;
        if (departmentName != null && departmentName.isEmpty()) departmentName = null;
        if (jobName != null && jobName.isEmpty()) jobName = null;

        EmployeeDAO.Filter employeeFilter = EmployeeDAO.getFilterBuilder()
                .first_name(firstName)
                .last_name(lastName)
                .middle_name(middleName)
                .build();
        List<Employee> employees = employeeDAO.searchEmployee(employeeFilter);

        for (Employee employee : employees) {
            EmployeeWrapper finalEmployee = new EmployeeWrapper(employee);
            if (departmentName == null || departmentName.equals(finalEmployee.getDepartmentName())) {
                if (experience == null || experience <= finalEmployee.getExperience()) {
                    if (jobName == null || jobName.equals(finalEmployee.getJob())) {
                        finalEmployees.add(finalEmployee);
                    }
                }
            }
        }

        model.addAttribute("employees", finalEmployees);
        return "employee_search";
    }
}
