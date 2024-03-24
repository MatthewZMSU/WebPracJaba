package cmc.msu.webpracjaba.DAO;

import cmc.msu.webpracjaba.models.EmployeeInfo;

import java.util.List;

public interface EmployeeInfoDAO extends CommonDAO<EmployeeInfo, Integer> {
    /*
     * Implements:
     * 1.) Creation
     * 2.) Reading
     * 3.) Updating
     * 4.) Searching
     * 6.) History searching
     * 6.) Deleting
     */

    List<EmployeeInfo> getEmployeeHistory(int employee_id);
}
