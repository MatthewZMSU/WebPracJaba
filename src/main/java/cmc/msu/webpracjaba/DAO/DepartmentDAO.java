package cmc.msu.webpracjaba.DAO;

import cmc.msu.webpracjaba.models.Department;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public interface DepartmentDAO extends CommonDAO<Department, Integer> {
    /*
     * Implements:
     * 1.) Creation
     * 2.) Reading
     * 3.) Updating
     * 4.) Searching
     * 5.) Getting structure of a department
     * 6.) Deleting
     */

    @Builder
    @Getter
    class Filter {
        private Integer department_id;
        private String name;
        private String directorLastName;
    }

    static Filter.FilterBuilder getFilterBuilder() {
        return Filter.builder();
    }

    List<Department> searchDepartment(Filter filter);

    List<Department> getDepartmentStructure(Integer department_id);
}
