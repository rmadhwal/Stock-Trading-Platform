package com.example.demo.Controllers;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.is;


import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.cs.trading.UsersDbApplication;
import com.cs.trading.Models.Trader;

import io.restassured.RestAssured;
import io.restassured.response.Response;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = UsersDbApplication.class)
public class AdminControllerTest {
	
	@LocalServerPort
	private int serverPort;
	
	@Before
	public void init(){
		RestAssured.port = serverPort;
	}
	
	@Test
	public void allTradersReturned() {
		Response response =
				given()
						.auth().basic("1", "{noop}123456")
						.accept(MediaType.APPLICATION_JSON_VALUE).
						when()
						.get("/traders").
						then()
						.statusCode(HttpStatus.SC_OK).
						and()
						.extract().response();

		Trader[] jsonResponse = response.as(Trader[].class);
		//extract the email of the first two traders
		assertEquals("siva.lim@gmail.com", jsonResponse[0].getEmail());
		assertEquals("xyz.tan@gmail.com", jsonResponse[1].getEmail());
	}
}
