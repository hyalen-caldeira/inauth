    package com.avenuecode.orders.webapi;

    import com.avenuecode.orders.Application;

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
    public class SearchIntegrationTest {
        private MockMvc mockMvc;
        @Autowired
        private WebApplicationContext context;

        @Before
        public void setup() {
            this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        }

        @Test
        public void when_ANonExistStatusIsGiven_then_NothingIsReturned() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/search?criteria=status:NON_EXIST").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0))).andDo(print());
        }

        @Test
        public void when_AValidStatusAndDiscountAreGiven_then_TheCorrectNumberOfOrdersAreReturned() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/search?criteria=status:SHIPPED,discount>0").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1))).andDo(print());
        }

        @Test
        public void when_AShippedStatusFilterIsGiven_then_TheCorrectNumberOfOrdersAreReturned() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/search?criteria=status:SHIPPED").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3))).andDo(print());
        }

        @Test
        public void when_AFulfilledStatusFilterIsGiven_then_TheCorrectNumberOfOrdersAreReturned() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/search?criteria=status:FULFILLED").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2))).andDo(print());
        }

        @Test
        public void when_AValidDiscountValueIsGiven_then_TheCorrectNumberOfOrdersContainingAtMininumThisDiscountAreReturned() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/search?criteria=discount>0").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2))).andDo(print());
        }

        @Test
        public void when_AValidNumberOfProductsIsGiven_then_TheCorrectNumberOfOrdersContainingAtMinimumThisNumberOfProductsAreReturned() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/search?criteria=products:2").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3))).andDo(print());
        }

        @Test
        public void when_AValidProductPriceIsGiven_then_TheCorrectNumberOfOrdersContainingAtMinimumThisPriceAreReturned() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/search?criteria=price>30").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4))).andDo(print());
        }

        @Test
        public void when_AnInvalidProductPriceIsGiven_then_NoOrderIsReturned() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/search?criteria=price>90").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0))).andDo(print());
        }
    }