package controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by azburatura on 8/18/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:webapp/WEB-INF/mvc-dispatcher-servlet.xml",
        "file:webapp/WEB-INF/spring-context.xml"})
@WebAppConfiguration
public class TestUploadFileController {

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testHandleUpload() throws Exception {

        MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());

        this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/upload")
                .file(firstFile))
                .andExpect(status().is(400));
    }

}
