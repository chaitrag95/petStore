package com.petstore.tests.Pet;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import com.github.javafaker.Faker;
import com.petstore.ResourceHelper.ResourceHelper;
import com.petstore.java.OrderPOJO;
import com.petstore.java.PetRequestPOJO;
import com.petstore.java.UsersPOJO;
import com.petstore.resources.ConfigReader;

import io.restassured.response.Response;

public class TestBase {

	public static ResourceHelper resourceHelper;
	public static ConfigReader configReader;
	public static Faker faker;

	public TestBase() {
		resourceHelper = new ResourceHelper();
		configReader = new ConfigReader();
		faker = new Faker();
	}
}
