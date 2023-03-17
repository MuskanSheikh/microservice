package org.micro;

import org.micro.repository.JobsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;
import org.testcontainers.utility.DockerImageName;

import java.nio.charset.StandardCharsets;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class AvailableJobsApplicationTests {

	static DockerImageName myImage = DockerImageName.parse("postgres").asCompatibleSubstituteFor("postgres");
	@Container
	static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer(myImage);


	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JobsRepository jobsRepository;

	@DynamicPropertySource
	public static void setProperty(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.jpa.datasource.url",postgreSQLContainer::getDatabaseName);
	}
	@Test
	void contextLoads() {
	}
	@Test
	void shouldCreateJob() throws Exception {
		ClassPathResource staticDataResource = new ClassPathResource("/json/create-job.json");
		String staticDataString = IOUtils.toString(staticDataResource.getInputStream(), StandardCharsets.UTF_8);
		byte[] jobJsonObject = (staticDataString).getBytes();

		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:9094/job-api/{userId}/add-job",1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jobJsonObject))
				.andExpect(MockMvcResultMatchers.status().isOk());
		jobsRepository.findAll();
		Assertions.assertTrue(true);
	}
	@Test
	void shouldGetAllJob() throws  Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:9094/job-api/get-all"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
