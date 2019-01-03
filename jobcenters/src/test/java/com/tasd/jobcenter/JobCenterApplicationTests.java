package com.tasd.jobcenter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.tasd.jobcenters.JobCenterController;
import com.tasd.jobcenters.JobCenterRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobCenterController.class)
@AutoConfigureMockMvc
public class JobCenterApplicationTests {

	@Autowired
	private JobCenterController controller;

	@MockBean
	private JobCenterRepository repo;
	
	@Autowired
    private MockMvc mockMvc;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
		assertThat(repo).isNotNull();
	}
	
    @Test
    public void getCenterByIdShouldReturnStatus200() throws Exception {
        //this.mockMvc.perform(get("/api/centers/2")).andExpect(status().isOk());
    }
    
    @Test
    public void createCenterTest() throws Exception {
        //this.mockMvc.perform(post("/api/centers")).andExpect(status().isOk());
    }

}
