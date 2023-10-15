package com.example.EurekaServer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import javax.swing.*;

@SpringBootTest
@EnableEurekaServer
public class EurekaServerApplicationTests {
public  static void main ( String [] args ) {
	SpringApplication.run(EurekaServerApplicationTests.class, args);
}
	@Test
	void contextLoads() {
	}

}
