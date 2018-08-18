package us.hyalen.orders.webapi;

import us.hyalen.orders.Application;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderIntegrationTest {
	private static final String ORDER_ID = "/1";
	private static final String NON_EXIST_ORDER_ID = "/999";

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void when_NonExistOrderIdIsGiven_then_ANotFoundStatusIsReturned() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/orders" + NON_EXIST_ORDER_ID).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}

	@Test
	public void when_ValidOrderIdIsGiven_then_AnOkStatusIsReturned() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/orders" + ORDER_ID).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}

	@Test
	public void testFetchAllOrders() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/orders").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(5))).andDo(print());
	}
}