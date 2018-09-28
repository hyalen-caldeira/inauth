package us.hyalen.inauth.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import us.hyalen.inauth.Application;
import us.hyalen.inauth.domain.Product;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
@SpringBootTest
public class ProductServiceTest {
	private static final String PRODUCT_ID = "1";
	private static final String NON_EXIST_PRODUCT_ID = "999";
	private static final int TOTAL_PRODUCTS = 5;

	@TestConfiguration
	static class ProductServiceTestContextConfiguration {
		@Bean
		public ProductService productService() {
			return new ProductService();
		}
	}

	@Autowired
	private ProductService productService;

	@Test
	public void testFetchAllProducts() {
		List<Product> result = productService.listProducts();
		assertEquals(TOTAL_PRODUCTS, result.size());
	}

	@Test
	public void when_AnExistProductIdIsGiven_then_ValidProductIsReturned() {
		Product result = productService.getProduct(PRODUCT_ID);
		assertEquals(PRODUCT_ID, result.getProductId());
	}

	@Test
	public void when_ANonExistProductIdIsGiven_then_NullIsReturned() {
		Product result = productService.getProduct(NON_EXIST_PRODUCT_ID);
		assertNull(result);
	}
}