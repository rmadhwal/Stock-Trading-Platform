package com.example.demo.Controllers;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cs.trading.UsersDbApplication;
import com.cs.trading.Models.Sector;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
classes = { UsersDbApplication.class} )
public class CompanyControllerTest {
 
	
	@LocalServerPort
	private int serverPort;
	@Before
	public void init(){
		RestAssured.port = serverPort;
	}
	
	@Test
	public void WhenListAllCompanies() {
		when()
		.get("/companies")
		.then()
		.statusCode(200)
		.body("size()", equalTo(4));
	}
	
	@Test
	public void WhenCreateCompany() {
		Map<String, Object>  map = new HashMap<>();
		map.put("symbol", "TEST_CREATE_NEW_TEST");
		map.put("sectorid", 1);
		map.put("name", "TEST_CREATE_NEW_TEST");
		
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
	        .body(map)
        .when()
        	.post("/companies/")
        .then()
			.statusCode(200);
	}
	
	@Test
	public void WhenDeleteOneCompany() {
		
		Map<String, Object>  map = new HashMap<>();
		map.put("symbol", "FB");
		
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
	        .body(map)
        .when()
        	.post("/companies/delete")
        .then()
			.statusCode(200);
		when()
		.get("/companies")
		.then()
		.statusCode(200)
		.body("size()", equalTo(3));
		
	}

}
