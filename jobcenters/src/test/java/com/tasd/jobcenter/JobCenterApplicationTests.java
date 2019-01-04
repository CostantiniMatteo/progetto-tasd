package com.tasd.jobcenter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tasd.jobcenters.JobCenterController;
import com.tasd.jobcenters.JobCenterEntity;
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
    	/*JobCenterEntity jobCenterEntity = new JobCenterEntity(2);
        this.mockMvc.perform(get("/api/centers/2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id", is(jobCenterEntity.getId())));*/

    }
    
    @Test
    public void createCenterTest() throws Exception {
    	/*ObjectMapper objectMapper = new ObjectMapper();
    	JobCenterEntity jobCenterEntity = new JobCenterEntity(2);
        this.mockMvc.perform(post("/api/centers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(jobCenterEntity)))
        .andExpect(status().isCreated());*/
    }

}
