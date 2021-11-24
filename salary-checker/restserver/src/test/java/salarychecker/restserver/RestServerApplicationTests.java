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

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import salarychecker.core.AbstractUser;
import salarychecker.core.AdminUser;
import salarychecker.core.User;
import salarychecker.json.SalaryCheckerPersistence;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(classes = { SalaryCheckerController.class, SalaryCheckerService.class, RestServerApplication.class })
@WebMvcTest
class RestServerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SalaryCheckerService salaryCheckerService;

	private ObjectMapper objectMapper;

	@Test
    public void contextLoads() throws Exception {
        assertNotNull(salaryCheckerService);
    }

	@BeforeAll
	public void setup()
	{
		objectMapper = SalaryCheckerPersistence.createObjectMapper();

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
	public void testGetAccounts() throws Exception 
	{
		mockMvc.perform(MockMvcRequestBuilders.get(getUrl())
			   								  .accept(MediaType.APPLICATION_JSON))
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andReturn();
	}

	@Test
	public void testGetUser() throws Exception 
	{
		mockMvc.perform(MockMvcRequestBuilders.get(getUrl("user?email=olanordmann@gmail.com"))
			   								  .accept(MediaType.APPLICATION_JSON))
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andReturn();
	}

	@Test
	public void testUserSignUp() throws Exception
	{
		final User user = new User("Seran", "Shanmugathas", "seran@live.no",
        	"Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130);
		final AdminUser adminUser =  new AdminUser("Hammad", "Siddiqui", "hammad@live.no",
        	"Password123!");
		this.salaryCheckerService.createUser(user);
		assertNotNull(this.salaryCheckerService.getUserByEmail("seran@live.no"));
		assertThrows(IllegalStateException.class, () -> {this.salaryCheckerService.createAdminUser(adminUser);});

		String userAsJson = objectMapper.writeValueAsString(user);
		String adminUserAsJson = objectMapper.writeValueAsString(adminUser);

		mockMvc.perform(MockMvcRequestBuilders.post(getUrl("create-user"))
											  .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
											  .content(userAsJson).accept(MediaType.APPLICATION_JSON))
			   .andExpect(MockMvcResultMatchers.status().isOk());

		mockMvc.perform(MockMvcRequestBuilders.post(getUrl("create-user", "admin"))
			   							      .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
			   							      .content(adminUserAsJson).accept(MediaType.APPLICATION_JSON))
			   .andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testUserSignIn() throws Exception
	{
		assertTrue(this.salaryCheckerService.userLogin("testemail@gmail.com", "Password123!"));
		assertFalse(this.salaryCheckerService.userLogin("testemail@gmail.com", "Hello "));
		assertFalse(this.salaryCheckerService.userLogin("email@example.com", "Password123!"));

		mockMvc.perform(MockMvcRequestBuilders.get(getUrl("users?employerEmail=employeer1@gmail.com"))
			   							      .accept(MediaType.APPLICATION_JSON))
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andReturn(); 
	}

	@Test
	public void testGetUsersByEmployerEmail() throws Exception
	{
		List<AbstractUser> employees = this.salaryCheckerService.getUsersByEmployerEmail("employeer1@gmail.com");
		assertTrue(employees.size() == 3);
		assertTrue(employees.stream().allMatch(user -> ((User) user).getEmployerEmail().equals("employeer1@gmail.com")));

		mockMvc.perform(MockMvcRequestBuilders.get(getUrl("users?employerEmail=employeer1@gmail.com"))
			   							      .accept(MediaType.APPLICATION_JSON))
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andReturn(); 

	}

	@Test
	public void testUpdateUserAttributes() throws Exception
	{
		Iterator<AbstractUser> iterator = salaryCheckerService.getAccounts().iterator();
		User updatedUser = new User("Ola", "Nordmann", "olanordmannEmail2@gmail.com",
			"Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130);

		String updatedUserAsString = objectMapper.writeValueAsString(updatedUser);

		if (iterator.hasNext()) {
			assertEquals("seran@live.no", iterator.next().getEmail());
		}
		if (iterator.hasNext()) {
			this.salaryCheckerService.updateUserAttributes(updatedUser, 0);
			assertNotNull(salaryCheckerService.getUserByEmail("olanordmannEmail2@gmail.com"));
			assertNull(salaryCheckerService.getUserByEmail("seran@live.no"));
		}

		mockMvc.perform(MockMvcRequestBuilders.put(getUrl("user", "update-profile"))
											  .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
											  .content(updatedUserAsString).accept(MediaType.APPLICATION_JSON))
			   .andExpect(MockMvcResultMatchers.status().isOk());
	}

	private String getUrl(String... segments) {
		String url = "/" + SalaryCheckerController.SALARY_CHECKER_SERVICE_PATH;
		for (String segment : segments) {
		  url = url + "/" + segment;
		}
		return url;
	}
}
