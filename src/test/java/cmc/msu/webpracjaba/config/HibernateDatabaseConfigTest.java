package cmc.msu.webpracjaba.config;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class HibernateDatabaseConfigTest {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Test
    public void connectionEstablishment() {
        SessionFactory sessionFactoryObject = this.sessionFactory.getObject();
        assertNotNull(sessionFactoryObject, "Established connection");

        Session session = sessionFactoryObject.openSession();
        assertNotNull(session, "Opened session");
    }
}
