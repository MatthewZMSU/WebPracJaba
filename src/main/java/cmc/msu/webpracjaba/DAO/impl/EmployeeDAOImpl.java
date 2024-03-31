package cmc.msu.webpracjaba.DAO.impl;

import cmc.msu.webpracjaba.DAO.EmployeeDAO;
import cmc.msu.webpracjaba.models.Employee;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAOImpl extends CommonDAOImpl<Employee, Integer> implements EmployeeDAO {
    public EmployeeDAOImpl() {
        super(Employee.class);
    }

    @Override
    public List<Employee> searchEmployee(Filter filter) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);

            List<Predicate> predicates = new ArrayList<>();
            if (filter.getEmployee_id() != null) {
                predicates.add(builder.equal(root.get("employee_id"), filter.getEmployee_id()));
            }
            if (filter.getFirst_name() != null) {
                predicates.add(builder.equal(root.get("first_name"), filter.getFirst_name()));
            }
            if (filter.getLast_name() != null) {
                predicates.add(builder.equal(root.get("last_name"), filter.getLast_name()));
            }
            if (filter.getMiddle_name() != null) {
                predicates.add(builder.equal(root.get("middle_name"), filter.getMiddle_name()));
            }

            if (!predicates.isEmpty()) {
                criteriaQuery.where(predicates.toArray(new Predicate[0]));
            }

            return session.createQuery(criteriaQuery).getResultList();
        }
    }
}
