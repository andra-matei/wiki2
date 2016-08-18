package controller;

import model.Word;
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

import static org.hamcrest.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by azburatura on 8/18/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:webapp/WEB-INF/mvc-dispatcher-servlet.xml",
        "file:webapp/WEB-INF/spring-context.xml"})
@WebAppConfiguration
public class TestHomeController {

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testShowHome() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/home.jsp"));
    }

    @Test
    public void testShowUpload() throws Exception {
        this.mockMvc.perform(get("/uploadFile"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/uploadFile.jsp"));
    }

    @Test
    public void testAddArticle() throws Exception {
        this.mockMvc.perform(post("/")
                .param("title", "<error>"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/words.jsp"));
    }
}
