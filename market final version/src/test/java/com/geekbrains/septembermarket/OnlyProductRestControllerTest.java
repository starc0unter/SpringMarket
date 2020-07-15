package com.geekbrains.septembermarket;

import com.geekbrains.septembermarket.configs.SecurityConfig;
import com.geekbrains.septembermarket.controllers.ProductRestController;
import com.geekbrains.septembermarket.entities.Product;
import com.geekbrains.septembermarket.repositories.ProductsRepository;
import com.geekbrains.septembermarket.services.ProductsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OnlyProductRestControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductsService productsService;

    // https://support.smartbear.com/alertsite/docs/monitors/api/endpoint/jsonpath.html

    @Test
    public void getAllProductsTest() throws Exception {
        List<Product> allProducts = Arrays.asList(
                new Product(1L, "Milk", new BigDecimal(90)),
                new Product(2L, "Bread", new BigDecimal(25)),
                new Product(3L, "Cheese", new BigDecimal(320))
        );

        given(productsService.findAll()).willReturn(allProducts);

        mvc.perform(get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].title", is(allProducts.get(0).getTitle())));
    }
}
