package cmc.msu.webpracjaba.DAO;

import cmc.msu.webpracjaba.models.EmployeeInfo;
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
public class EmployeeInfoDAOTest {
    @Autowired
    private EmployeeInfoDAO employeeInfoDAO;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    void getEmployeeHistoryByNull() {
        List<EmployeeInfo> employees = employeeInfoDAO.getEmployeeHistory(null);
        assertNotNull(employees);
        assertEquals(0, employees.size());
    }

    @Test
    void getEmployeeHistoryById2() {
        List<EmployeeInfo> employees = employeeInfoDAO.getEmployeeHistory(2);
        assertNotNull(employees);
        assertEquals(1, employees.size());
    }

    @Test
    void getEmployeeHistoryById3() {
        List<EmployeeInfo> employees = employeeInfoDAO.getEmployeeHistory(3);
        assertNotNull(employees);
        assertEquals(2, employees.size());
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
