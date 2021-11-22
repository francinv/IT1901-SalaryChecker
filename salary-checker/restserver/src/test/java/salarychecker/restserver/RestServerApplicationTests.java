package salarychecker.restserver;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import salarychecker.core.AbstractUser;
import salarychecker.core.AdminUser;
import salarychecker.core.User;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(classes = { SalaryCheckerController.class, SalaryCheckerService.class, RestServerApplication.class })
class RestServerApplicationTests {

	@Autowired
	private SalaryCheckerService salaryCheckerService;

	@Test
    public void contextLoads() throws Exception {
        assertNotNull(salaryCheckerService);
    }

	@BeforeAll
	public void setup()
	{
		final User employee1 = new User("Ola", "Nordmann", "olanordmann@gmail.com",
        "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130);
		final User employee2 = new User("Kari", "Nordmann", "karinordmann@gmail.com",
        "Password123!", "22030191349", 12345, "employeer2@gmail.com", 30.0, 130);
		final User employee3 = new User("Seran", "Shanmugathas", "testemail@gmail.com",
        "Password123!", "22030191349", 12345, "employeer3@gmail.com", 30.0, 130);

		this.salaryCheckerService.createUser(employee1);
		this.salaryCheckerService.createUser(employee2);
		this.salaryCheckerService.createUser(employee3);	
	}

	@Test
	public void testUserSignUp()
	{
		String email = "seran@live.no";
		final User user1 = new User("Seran", "Shanmugathas", email,
        "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130);
		final User user2 =  new User("Hammad", "Siddiqui", email,
        "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130);
		this.salaryCheckerService.createUser(user1);
		assertNotNull(this.salaryCheckerService.getUserByEmail(email));
		assertThrows(IllegalStateException.class, () -> {this.salaryCheckerService.createUser(user2);});
	}

	@Test
	public void testUserSignIn()
	{
		assertTrue(this.salaryCheckerService.userLogin("testemail@gmail.com", "Password123!"));
		assertFalse(this.salaryCheckerService.userLogin("testemail@gmail.com", "Hello "));
		assertFalse(this.salaryCheckerService.userLogin("email@example.com", "Password123!"));
	}

	@Test
	public void testGetUsersByEmployerEmail()
	{
		List<AbstractUser> employees = this.salaryCheckerService.getUsersByEmployerEmail("employeer1@gmail.com");
		assertTrue(employees.size() == 3);
		assertTrue(employees.stream().allMatch(user -> ((User) user).getEmployerEmail().equals("employeer1@gmail.com")));

	}

	@Test
	public void testUpdateUserAttributes()
	{
		Iterator<AbstractUser> iterator = salaryCheckerService.getAccounts().iterator();
		if (iterator.hasNext()) {
			assertEquals("seran@live.no", iterator.next().getEmail());
		}
		if (iterator.hasNext()) {
			this.salaryCheckerService.updateUserAttributes(new User("Ola", "Nordmann", "olanordmannEmail2@gmail.com",
				"Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130), 0);
			assertNotNull(salaryCheckerService.getUserByEmail("olanordmannEmail2@gmail.com"));
			assertNull(salaryCheckerService.getUserByEmail("seran@live.no"));
		}
	}
}
