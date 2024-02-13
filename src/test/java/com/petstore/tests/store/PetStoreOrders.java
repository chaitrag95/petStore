package com.petstore.tests.store;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.petstore.ResourceHelper.ResourceHelper;
import com.petstore.java.OrderPOJO;
import com.petstore.java.PetRequestPOJO;
import com.petstore.resources.ConfigReader;
import com.petstore.tests.Pet.TestBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PetStoreOrders extends TestBase {

	private static OrderPOJO orderPOJO;
	private static int orderId = Integer.parseInt(ConfigReader.getId());
	private static int petId = Integer.parseInt(ConfigReader.getPetId());
	private static int invalidOrderId = Integer.parseInt(configReader.getInvalidOrderId());

	public PetStoreOrders() {
		super();
		
	}
	
	@BeforeMethod
	public void setUp() {
		orderPOJO = new OrderPOJO();
	}

	@Test(priority=1)
	public void orderPet() {
		int orderQuantity = Integer.parseInt(configReader.getOrderQuantity());
		orderPOJO.setId(orderId);
		orderPOJO.setPetId(petId);
		orderPOJO.setQuantity(orderQuantity);
		orderPOJO.setShipDate(configReader.getOrderShippedDate());
		orderPOJO.setStatus(configReader.getOrderStatus());
		orderPOJO.setComplete(true);

		Response response = resourceHelper.orderPet(orderPOJO);

		Assert.assertEquals(response.getStatusCode(), 200);

		System.out.println("Response Body: " + response.getBody().asString());

		System.out.println("Response headers: " + response.getHeaders());

		response.prettyPrint();

	}
	
	@Test(priority=2)
	public void getInventoriesByStatus() {

		Response response = resourceHelper.getInventoriesByStatus();

		Assert.assertEquals(response.getStatusCode(), 200);

		System.out.println("Response Body: " + response.getBody().asString());

		System.out.println("Response headers: " + response.getHeaders());

		response.prettyPrint();
	}
	
	@Test(priority=3)
	public void testFindPetsByStatus() {

		Response response = resourceHelper.findPurchaseOrderById(orderId);

		Assert.assertEquals(response.getStatusCode(), 200);

		System.out.println("Response Body: " + response.getBody().asString());

		System.out.println("Response Headers: " + response.getHeaders());

		response.prettyPrint();
	}
	
	@Test(priority=4)
	public void testFindPetsByStatusWithInvalidOrderId() {

		Response response = resourceHelper.findPurchaseOrderById(invalidOrderId);

		Assert.assertEquals(response.getStatusCode(), 404);

		System.out.println("Response Body: " + response.getBody().asString());

		System.out.println("Response Headers: " + response.getHeaders());

		response.prettyPrint();
	}
	
	@Test(priority=5)
	public void deletePet() {

		Response response = resourceHelper.getDeletePurchaseOrderById(orderId);

		Assert.assertEquals(response.getStatusCode(), 200);

		System.out.println("Response Body: " + response.getBody().asString());

		System.out.println("Response headers: " + response.getHeaders());

		response.prettyPrint();
	}
	
	@Test(priority=6)
	public void deletePetWithInvalidOrderId() {

		Response response = resourceHelper.getDeletePurchaseOrderById(invalidOrderId);

		Assert.assertEquals(response.getStatusCode(), 404);

		System.out.println("Response Body: " + response.getBody().asString());

		System.out.println("Response headers: " + response.getHeaders());

		response.prettyPrint();
	}
}
