package com.example.demo.Controllers;

import static org.junit.Assert.*;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matchers;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cs.trading.UsersDbApplication;
import io.restassured.RestAssured;
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
classes = { UsersDbApplication.class} )

public class MarketControllerTest {
 
	@LocalServerPort
	private int serverPort;
	
	@Before
	public void init(){
		RestAssured.port = serverPort;
	}
	
	@Test
	public void whenGetSectorSummaryWithInvalidSectorIdShouldReturnNull() {
		when()
		.get("/compositeIndex/5555")
		.then()
		.body("message", Matchers.equalToIgnoringCase("Invalid sector id"));
		
	}

	@Test
	public void whenGetSectorSummaryWithValidSectorIdShouldReturn() {
		when()
		.get("/compositeIndex/44")
		.then()
		.body("sectorName", Matchers.equalToIgnoringCase("test companies"));
		
	}
	
	@Test
	public void whenGetSummaryAscOrderThenFirstElementVolumeShouldBe150() {
		when()
		.get("/marketSummary?order=asc")
		.then()
		.body("[0].volume", equalTo(150));
		
	}
	
	@Test
	public void whenGetSummaryDescOrderThenFirstElementVolumeShouldBe200() {
		when()
		.get("/marketSummary?order=des")
		.then()
		.body("[0].volume", equalTo(200));
		
	}
	 
	
	
}
