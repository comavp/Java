package ru.comavp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import ru.comavp.dto.Company;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyControllerTest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetCompany() throws Exception {
        restTemplate.getForObject("http://localhost:" + port + "/get", Company.class);
        restTemplate.getForObject("http://localhost:" + port + "/get/error", Company.class);
    }
}
