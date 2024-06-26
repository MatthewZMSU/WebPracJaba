package cmc.msu.webpracjaba.DAO;

import cmc.msu.webpracjaba.models.Employee;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public interface EmployeeDAO extends CommonDAO<Employee, Integer> {
    /*
     * Implements:
     * 1.) Creation
     * 2.) Reading
     * 3.) Updating
     * 4.) Searching
     * 5.) Deleting
     */

    @Builder
    @Getter
    class Filter {
        private Integer employee_id;
        private String first_name;
        private String last_name;
        private String middle_name;
    }

    static Filter.FilterBuilder getFilterBuilder() {
        return Filter.builder();
    }

    List<Employee> searchEmployee(Filter filter);
}
