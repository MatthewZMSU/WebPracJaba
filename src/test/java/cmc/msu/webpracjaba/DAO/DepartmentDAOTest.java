package cmc.msu.webpracjaba.DAO;

import cmc.msu.webpracjaba.models.Department;
import cmc.msu.webpracjaba.models.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application-test.properties")
public class DepartmentDAOTest {
    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    void searchDepartmentByNull() {
        DepartmentDAO.Filter.FilterBuilder filter = new DepartmentDAO.Filter.FilterBuilder();

        List<Department> departments = departmentDAO.searchDepartment(filter.build());
        assertNotNull(departments);
        assertEquals(7, departments.size());
    }

    @Test
    void searchDepartmentByDirector() {
        DepartmentDAO.Filter.FilterBuilder filter = new DepartmentDAO.Filter.FilterBuilder();
        filter.directorLastName("Николаев");

        List<Department> departments = departmentDAO.searchDepartment(filter.build());
        assertNotNull(departments);
        assertEquals(3, departments.size());
    }

    @Test
    void searchDepartmentByName() {
        DepartmentDAO.Filter.FilterBuilder filter = new DepartmentDAO.Filter.FilterBuilder();
        filter.name("Аналитика");

        List<Department> departments = departmentDAO.searchDepartment(filter.build());
        assertNotNull(departments);
        assertEquals(1, departments.size());
    }

    @Test
    void searchDepartmentById() {
        DepartmentDAO.Filter.FilterBuilder filter = new DepartmentDAO.Filter.FilterBuilder();
        filter.department_id(3);

        List<Department> departments = departmentDAO.searchDepartment(filter.build());
        assertNotNull(departments);
        assertEquals(1, departments.size());
    }

    @Test
    void searchDepartmentByAll() {
        DepartmentDAO.Filter.FilterBuilder filter = new DepartmentDAO.Filter.FilterBuilder();
        filter.name("Аналитика");
        filter.department_id(1);
        filter.directorLastName("Викторова");

        List<Department> departments = departmentDAO.searchDepartment(filter.build());
        assertNotNull(departments);
        assertEquals(0, departments.size());
    }

    @Test
    void getDepartmentStructureHead() {
        List<Department> departments = departmentDAO.getDepartmentStructure(1);
        assertNotNull(departments);
        assertEquals(3, departments.size());
        assertNull(departments.getFirst());
        assertEquals(3, departments.get(1).getDepartment_id());
        assertEquals(4, departments.get(2).getDepartment_id());
    }

    @Test
    void getDepartmentStructureTail() {
        List<Department> departments = departmentDAO.getDepartmentStructure(7);
        assertNotNull(departments);
        assertEquals(1, departments.size());
        assertEquals(2, departments.getFirst().getDepartment_id());
    }

    @Test
    void InsertDeleteDepartment() {
        Employee director = employeeDAO.getById(2);
        assertNotNull(director);
        Department department = new Department(0, "НьюДепартмент", director);
        departmentDAO.save(department);
        List<Department> all_departments = (List<Department>) departmentDAO.getAll();
        assertEquals(all_departments.size(), 8);
        Department addedDepartment = all_departments.get(7);
        departmentDAO.deleteById(addedDepartment.getId());
        all_departments = (List<Department>) departmentDAO.getAll();
        assertEquals(all_departments.size(), 7);
    }

    @BeforeAll
    @AfterEach
    void annihilation() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.getTransaction().commit();
        }
    }
}
