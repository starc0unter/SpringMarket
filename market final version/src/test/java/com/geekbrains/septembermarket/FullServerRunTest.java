package com.geekbrains.septembermarket;

import com.geekbrains.septembermarket.entities.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FullServerRunTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void exampleTest() {
        List<Product> products = this.restTemplate.getForObject("/api/v1/products", List.class);
        assertThat(products.size()).isEqualTo(0);
    }
}
