package salarychecker.ui;

import java.net.URI;
import java.net.URISyntaxException;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.User;
import salarychecker.ui.controllers.SalaryCheckerConfig;

public class RemoteSalaryCheckerAccessTest {
    
    private WireMockConfiguration wireMockConfig;
    private WireMockServer wireMockServer;

    private RemoteSalaryCheckerAccess remoteSalaryCheckerAccess;

    @BeforeAll
    public void setup() throws URISyntaxException {
        wireMockConfig = WireMockConfiguration.wireMockConfig().port(8080);
        wireMockServer = new WireMockServer(wireMockConfig.portNumber());
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockConfig.portNumber());
        SalaryCheckerConfig salaryCheckerConfig = new SalaryCheckerConfig();
        remoteSalaryCheckerAccess = new RemoteSalaryCheckerAccess(
            new URI(salaryCheckerConfig.getProperty("serverURI"))
            );

        final User employee1 = new User("Ola", "Nordmann", "olanordmann@gmail.com",
            "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130);
        final User employee2 = new User("Kari", "Nordmann", "karinordmann@gmail.com",
            "Password123!", "22030191349", 12345, "employeer2@gmail.com", 30.0, 130);
        final AdminUser adminUser = new AdminUser("Seran", "Shanmugathas", "testemail@gmail.com",
            "Password123!");
        
        final Accounts accounts = new Accounts();
        accounts.addUser(employee1);
        accounts.addUser(employee2);
        accounts.addUser(adminUser);
    }

    @Test
    public void testGetAccounts() {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(getUrl()))
            .withHeader("Accept", WireMock.equalTo("application/json"))
            .willReturn(WireMock.aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
            )
        );
    }

    @Test
	public void testGetUser() throws Exception {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(getUrl("user?email=olanordmann@gmail.com")))
            .withHeader("Accept", WireMock.equalTo("application/json"))
            .willReturn(WireMock.aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
            )
        );
	}

	@Test
	public void testUserSignUp() throws Exception {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(getUrl("create-user")))
            .withHeader("Accept", WireMock.equalTo("application/json"))
            .willReturn(WireMock.aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
            )
        );
	}

	@Test
	public void testUserSignIn() {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(getUrl("login?email={email}&password={password}")))
            .withHeader("Accept", WireMock.equalTo("application/json"))
            .willReturn(WireMock.aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
            )
        );
	}

	@Test
	public void testGetUsersByEmployerEmail() throws Exception {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(getUrl("users?employerEmail=employeer1@gmail.com")))
            .withHeader("Accept", WireMock.equalTo("application/json"))
            .willReturn(WireMock.aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
            )
        );
	}

	@Test
	public void testUpdateUserAttributes() throws Exception {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(getUrl("user", "update-profile")))
        .withHeader("Accept", WireMock.equalTo("application/json"))
        .willReturn(WireMock.aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
        )
    );
	}



    @AfterEach
    public void stopWireMockServer() {
     wireMockServer.stop();
    }

    private String getUrl(String... segments) {
		String url = "/salarychecker";
		for (String segment : segments) {
		  url = url + "/" + segment;
		}
		return url;
	}

    public RemoteSalaryCheckerAccess getRemoteSalaryCheckerAccess() {
        return remoteSalaryCheckerAccess;
    }

}
