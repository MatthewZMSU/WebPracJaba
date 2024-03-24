package cmc.msu.webpracjaba.DAO.impl;

import cmc.msu.webpracjaba.DAO.DepartmentDAO;
import cmc.msu.webpracjaba.models.Department;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentDAOImpl extends CommonDAOImpl<Department, Integer> implements DepartmentDAO {
    public DepartmentDAOImpl() {
        super(Department.class);
    }

    @Override
    public List<Department> searchDepartment(Filter filter) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Department> criteriaQuery = builder.createQuery(Department.class);
            Root<Department> root = criteriaQuery.from(Department.class);

            List<Predicate> predicates = new ArrayList<>();
            if (filter.getDepartment_id() != null) {
                predicates.add(builder.equal(root.get("department_id"), filter.getDepartment_id()));
            }
            if (filter.getName() != null) {
                predicates.add(builder.equal(root.get("name"), filter.getName()));
            }
            if (filter.getDirector() != null) {
                predicates.add(builder.equal(root.get("director").get("employee_id"), filter.getDirector()));
            }

            if (!predicates.isEmpty()) {
                criteriaQuery.where(predicates.toArray(new Predicate[0]));
            }

            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception err) {
            System.out.println("Got an exception in searchDepartment method.\nThe exception:\n" + err);
            return null;
        }
    }

    @Override
    public List<Department> getDepartmentStructure(Integer department_id) {
        List<Department> departments = new ArrayList<>();
        departments.add(getById(department_id).getHead_department());  // Head department (maybe null)
        for (Department department : getAll()) {  // All sub-departments
            Department headDepartment = department.getHead_department();
            if (headDepartment != null && headDepartment.getDepartment_id().equals(department_id)) {
                departments.add(department);
            }
        }
        return departments;
    }
}
