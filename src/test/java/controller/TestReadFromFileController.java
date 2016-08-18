package controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Created by azburatura on 8/18/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:webapp/WEB-INF/mvc-dispatcher-servlet.xml",
        "file:webapp/WEB-INF/spring-context.xml"})
@WebAppConfiguration
/**
 * (non Java-doc)
 *
 * Test for ReadFromFileController.java
 */
public class TestReadFromFileController {

    /**
     * Injected WebApplicationContext bean
     */
    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    /**
     * building the web application context
     */
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * testing the method readFromFile()
     * @throws Exception
     */
    @Test
    public void testReadFromFile() throws Exception {
        this.mockMvc.perform(post("/readFile"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/words.jsp"));
    }

}
