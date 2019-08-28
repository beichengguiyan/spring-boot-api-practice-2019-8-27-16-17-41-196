package com.tw.apistackbase;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyResourceTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void should_return_ok_and_content_when_query_all_company() throws Exception {
		//Given
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/companies/");
		//When
		ResultActions performResult = mockMvc.perform(requestBuilder);
		//Then
		performResult
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("alibaba")));
	}

	@Test
	public void should_return_201_status_when_create_company() throws Exception {
		//Given
		MockHttpServletRequestBuilder input = post("/companies").content("{\r\n" + 
				"        \"id\": 0,\r\n" + 
				"        \"companyName\": \"alibaba\",\r\n" + 
				"        \"employeesNumber\": 200,\r\n" + 
				"        \"employees\": [\r\n" + 
				"            {\r\n" + 
				"                \"id\": 4,\r\n" + 
				"                \"name\": \"alibaba1\",\r\n" + 
				"                \"age\": 20,\r\n" + 
				"                \"gender\": \"male\",\r\n" + 
				"                \"salary\": 0\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"id\": 11,\r\n" + 
				"                \"name\": \"tengxun2\",\r\n" + 
				"                \"age\": 19,\r\n" + 
				"                \"gender\": \"female\",\r\n" + 
				"                \"salary\": 0\r\n" + 
				"            }]\r\n" + 
				"    }").contentType(MediaType.APPLICATION_JSON);
		//When
		ResultActions result = mockMvc.perform(input);
		//Then
		result
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isCreated());
	}
	
	@Test
	public void should_return_201_status_when_update_company() throws Exception {
		//Given
		MockHttpServletRequestBuilder input = put("/companies/1").content("{\r\n" + 
				"        \"id\": 0,\r\n" + 
				"        \"companyName\": \"alibaba\",\r\n" + 
				"        \"employeesNumber\": 200,\r\n" + 
				"        \"employees\": [\r\n" + 
				"            {\r\n" + 
				"                \"id\": 4,\r\n" + 
				"                \"name\": \"alibaba1\",\r\n" + 
				"                \"age\": 20,\r\n" + 
				"                \"gender\": \"male\",\r\n" + 
				"                \"salary\": 0\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"id\": 11,\r\n" + 
				"                \"name\": \"tengxun2\",\r\n" + 
				"                \"age\": 19,\r\n" + 
				"                \"gender\": \"female\",\r\n" + 
				"                \"salary\": 0\r\n" + 
				"            }]\r\n" + 
				"    }").contentType(MediaType.APPLICATION_JSON);
		//When
		ResultActions result = mockMvc.perform(input);
		//Then
		result
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isCreated());
	}

}
