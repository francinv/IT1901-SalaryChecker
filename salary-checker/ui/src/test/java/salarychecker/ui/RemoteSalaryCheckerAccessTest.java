package salarychecker.ui;

import java.net.URI;
import java.net.URISyntaxException;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import salarychecker.ui.controllers.SalaryCheckerConfig;

public class RemoteSalaryCheckerAccessTest {
    
    private WireMockConfiguration wireMockConfig;
    private WireMockServer wireMockServer;

    private RemoteSalaryCheckerAccess remoteSalaryCheckerAccess;

    @BeforeEach
    public void setup() throws URISyntaxException {
        wireMockConfig = WireMockConfiguration.wireMockConfig().port(8080);
        wireMockServer = new WireMockServer(wireMockConfig.portNumber());
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockConfig.portNumber());
        SalaryCheckerConfig salaryCheckerConfig = new SalaryCheckerConfig();
        remoteSalaryCheckerAccess = new RemoteSalaryCheckerAccess(
            new URI(salaryCheckerConfig.getProperty("serverURI"))
            );
    }

    @Test
    public void testGetAccounts() {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/salarychecker"))
            .withHeader("Accept", WireMock.equalTo("application/json"))
            .willReturn(WireMock.aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"Accounts\": [ {\"firstname\": \"Seran\", \"lastname\": \"Shanmugathas\", \"email\": \"seran@live.no\"} ]}")
            )
        );
    }

    @AfterEach
    public void stopWireMockServer() {
     wireMockServer.stop();
    }

}
