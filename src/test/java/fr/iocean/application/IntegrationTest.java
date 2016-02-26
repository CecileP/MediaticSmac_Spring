package fr.iocean.application;

import javax.annotation.PostConstruct;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import fr.iocean.application.helper.JsonHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FilRougeApplication.class)
@WebAppConfiguration
public class IntegrationTest {
	
	@Autowired
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    protected JsonHelper jsonHelper = new JsonHelper();

    @PostConstruct
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }
	
}
