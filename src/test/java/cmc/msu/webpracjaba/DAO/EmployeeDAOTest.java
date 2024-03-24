package cmc.msu.webpracjaba.DAO;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application-test.properties")
public class EmployeeDAOTest {
    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    void searchEmployeeByNull() {
        EmployeeDAO.Filter.FilterBuilder filter = new EmployeeDAO.Filter.FilterBuilder();

        List<Employee> employees = employeeDAO.searchEmployee(filter.build());
        assertNotNull(employees);
        assertEquals(6, employees.size());
    }

    @Test
    void searchEmployeeById() {
        EmployeeDAO.Filter.FilterBuilder filter = new EmployeeDAO.Filter.FilterBuilder();
        filter.employee_id(3);

        List<Employee> employees = employeeDAO.searchEmployee(filter.build());
        assertNotNull(employees);
        assertEquals(1, employees.size());
    }

    @Test
    void searchEmployeeByFirstName() {
        EmployeeDAO.Filter.FilterBuilder filter = new EmployeeDAO.Filter.FilterBuilder();
        filter.first_name("Кирилл");

        List<Employee> employees = employeeDAO.searchEmployee(filter.build());
        assertNotNull(employees);
        assertEquals(1, employees.size());
    }

    @Test
    void searchEmployeeBySecondName() {
        EmployeeDAO.Filter.FilterBuilder filter = new EmployeeDAO.Filter.FilterBuilder();
        filter.last_name("Викторова");

        List<Employee> employees = employeeDAO.searchEmployee(filter.build());
        assertNotNull(employees);
        assertEquals(1, employees.size());
    }

    @Test
    void searchEmployeeByMiddleName() {
        EmployeeDAO.Filter.FilterBuilder filter = new EmployeeDAO.Filter.FilterBuilder();
        filter.middle_name("Валерьевич");

        List<Employee> employees = employeeDAO.searchEmployee(filter.build());
        assertNotNull(employees);
        assertEquals(0, employees.size());
    }

    @Test
    void searchEmployeeByAll() {
        EmployeeDAO.Filter.FilterBuilder filter = new EmployeeDAO.Filter.FilterBuilder();
        filter.first_name("Юлия");
        filter.last_name("Викторова");
        filter.middle_name("Андревич");

        List<Employee> employees = employeeDAO.searchEmployee(filter.build());
        assertNotNull(employees);
        assertEquals(0, employees.size());
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
