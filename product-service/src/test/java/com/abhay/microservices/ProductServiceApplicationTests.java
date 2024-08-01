package com.abhay.microservices;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;


@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {
	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup(){
		RestAssured.baseURI="http://localhost";
		RestAssured.port= port;
	}
	static {
		mongoDBContainer.start();
	}
	@Test
	void contextLoads() {
		String requestBody= """
				{
				    "id": "66a90c09e4b5b93d54b2ca75",
				    "name": "abhay",
				    "description": "This is iPhone",
				    "price": 1000
				}""";
		RestAssured
				.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("iphone15"))
				.body("description", Matchers.equalTo("Iphone 15 is smartphone of apple "))
				.body("price", Matchers.equalTo(1000));
	}

}
