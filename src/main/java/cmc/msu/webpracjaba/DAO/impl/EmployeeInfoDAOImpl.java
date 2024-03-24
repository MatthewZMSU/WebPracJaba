package cmc.msu.webpracjaba.DAO.impl;

import cmc.msu.webpracjaba.DAO.EmployeeInfoDAO;
import cmc.msu.webpracjaba.models.EmployeeInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class EmployeeInfoDAOImpl extends CommonDAOImpl<EmployeeInfo, Integer> implements EmployeeInfoDAO {
    public EmployeeInfoDAOImpl() {
        super(EmployeeInfo.class);
    }

    @Autowired
    private EmployeeInfoDAO employeeInfoDAO = new EmployeeInfoDAOImpl();

    @Override
    public List<EmployeeInfo> getEmployeeHistory(Integer employee_id) {
        List<EmployeeInfo> employeeInfos = new ArrayList<>();
        for (EmployeeInfo employeeInfo : employeeInfoDAO.getAll()) {
            if (employeeInfo.getEmployee_id().getEmployee_id().equals(employee_id)) {
                employeeInfos.add(employeeInfo);
            }
        }
        return employeeInfos;
    }
}
