package edu.udacity.java.nano;

import edu.udacity.java.nano.chat.Message;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest
public class WebSocketChatApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	private Message message;

	@Test
	public void login() throws Exception {
//		this.mockMvc.perform(get("/")).andExpect(status().isOk())
//				.andExpect(view().name("/login"));
		MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get("/")) .andExpect(status().isOk()) .
				andExpect(content().contentType("text/html;charset=UTF-8")) .andReturn();
		String results = result.getResponse().getContentAsString();
		Assert.assertThat(results, CoreMatchers.containsString("Login"));

	}

	@Test
	public void chat() throws Exception
	{
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/index?username=Sowmya"))
				.andExpect(status().isOk())
		        .andExpect(content().contentType("text/html;charset=UTF-8")).andReturn();
		String results = result.getResponse().getContentAsString();
		Assert.assertThat(results, CoreMatchers.containsString("username"));
//				.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("Sowmya"));
	}



}
