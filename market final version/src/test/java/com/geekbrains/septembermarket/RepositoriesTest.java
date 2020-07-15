package com.geekbrains.septembermarket;

import com.geekbrains.septembermarket.entities.Product;
import com.geekbrains.septembermarket.entities.Role;
import com.geekbrains.septembermarket.repositories.ProductsRepository;

import com.geekbrains.septembermarket.repositories.RoleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
// @TestPropertySource(locations="classpath:test.properties")
public class RepositoriesTest {
    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void productRepositoryTest() {
        Product product = new Product(null, "Bread", new BigDecimal(25.0f));
        Product out = entityManager.persist(product);
        entityManager.flush();

        List<Product> productsList = (List<Product>)productsRepository.findAll();
        System.out.println(productsList);

        Assert.assertEquals(1, productsList.size());
    }
}
