package com.geekbrains.geekmarketwinter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class GeekMarketWinterApplication {
	public static void main(String[] args) {
		SpringApplication.run(GeekMarketWinterApplication.class, args);
	}

//	private TestRepository repository;
//
//	public GeekMarketWinterApplication(@Autowired TestRepository repository) {
//		this.repository = repository;
//	}

//	@Override
//	public void run(String... args) throws Exception {
//		List<ProductDTO> res = repository.getProducts(1).stream().collect(toCollection(ArrayList::new));
//		System.out.println(res);
//	}
}