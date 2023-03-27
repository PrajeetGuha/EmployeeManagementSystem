package org.antwalk.ems.repository;

import org.antwalk.ems.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EmployeeRepositoryTest {
    @Autowired
    private TestEntityManager em;
  
    @Autowired
    private EmployeeRepository repository;
  
    @Test
    public void contextLoads() {
      Assertions.assertNotNull(em);
    }

    @Test
    void verifyBootstrappingByPersistingAnEmployee() {
      Employee emp = new Employee();
      emp.setBranch("Kolkata");
      emp.setEmpName("Darkhash");
      emp.setGender("male");

      Assertions.assertNull(emp.getEmpId());
      em.persist(emp);
      Assertions.assertNotNull(emp.getEmpId());
      Assertions.assertEquals(emp.getBranch(), "Kolkata");
      Assertions.assertEquals(emp.getEmpName(), "Darkhash");
      Assertions.assertEquals(emp.getGender(), "male");
    }
  
    @Test
    void verifyRepositoryByPersistingAnEmployee() {
      Employee emp = new Employee();
      emp.setBranch("Kolkata");
      emp.setEmpName("Darkhash");
      emp.setGender("male");
  
      Assertions.assertNull(emp.getEmpId());
      repository.save(emp);
      Assertions.assertNotNull(emp.getEmpId());
      Assertions.assertEquals(emp.getBranch(), "Kolkata");
      Assertions.assertEquals(emp.getEmpName(), "Darkhash");
      Assertions.assertEquals(emp.getGender(), "male");
    }
}
