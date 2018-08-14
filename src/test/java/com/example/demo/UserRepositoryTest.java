package com.example.demo;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.cs.trading.UsersDbApplication;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT, classes= {UsersDbApplication.class})
public class UserRepositoryTest {

	
	@LocalServerPort
    private int serverPort;
	
	@Before
	public void setUp() {
		RestAssured.port = serverPort;
	}
	
	@Test
	public void return200OnCallToRepo() {
		given().auth().basic("john", "smith").when().get("/users").then().statusCode(200);
	}
	
	@Test
	public void returnsCorrectUser() {
		given().auth().basic("john", "smith").when().get("/users/2").then().body("id", equalTo(2));
		
	}
	
}
