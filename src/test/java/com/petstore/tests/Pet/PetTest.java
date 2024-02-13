package com.petstore.tests.Pet;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.petstore.ResourceHelper.ResourceHelper;
import com.petstore.java.PetCategoryPOJO;
import com.petstore.java.PetRequestPOJO;
import com.petstore.java.PetResponsePOJO;
import com.petstore.java.TagPOJO;
import com.petstore.resources.ConfigReader;

import io.restassured.response.Response;

public class PetTest extends TestBase {

	private static PetRequestPOJO petRequestPOJO;
	private static int petId = Integer.parseInt(configReader.getPetId());
	private static String petName = configReader.getPetName();
	private static String updatePetName = configReader.getUpdatePetName();
	private static String updatePetStatus = configReader.getUpdatePetStatus();
	private static int invalidPetId = Integer.parseInt(configReader.getInvalidPetId());

	public PetTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		petRequestPOJO = new PetRequestPOJO();
	}

	@Test(priority = 1)
	public void addNewPetToStore() {

		PetRequestPOJO petRequestPOJO = createRandomPetRequest();

		Response response = ResourceHelper.postAddPet(petRequestPOJO);

		PetResponsePOJO petResponsePOJO = ResourceHelper.postAddPet(petRequestPOJO).as(PetResponsePOJO.class);

		Assert.assertEquals(response.getStatusCode(), 200);

		assertEquals(petRequestPOJO.getName(), petResponsePOJO.getName());

		System.out.println("Response Body: " + response.getBody().asString());

		response.prettyPrint();

	}

	private PetRequestPOJO createRandomPetRequest() {
		Faker faker = new Faker();
		PetCategoryPOJO category = new PetCategoryPOJO();
		category.setId(faker.number().digit());
		category.setName(faker.name().firstName());
		petRequestPOJO.setCategory(category);
		petRequestPOJO.setId(petId);
		petRequestPOJO.setName(petName);
		List<TagPOJO> tagPOJOs = new ArrayList<>();
		TagPOJO tagPOJO = new TagPOJO();
		tagPOJO.setId(faker.number().randomNumber());
		tagPOJO.setName(faker.lorem().word());
		tagPOJOs.add(tagPOJO);
		petRequestPOJO.setTags(tagPOJOs);
		ArrayList<String> photoUrlList = new ArrayList<String>();
		photoUrlList.add("dummyPhotoURL");
		petRequestPOJO.setPhotoUrls(photoUrlList);
		petRequestPOJO.setStatus("available");

		return petRequestPOJO;
	}

	@Test
	public int findPetById() {

		Response response = resourceHelper.findPetById(petId);

		Assert.assertEquals(response.getStatusCode(), 200);

		System.out.println("Response Body: " + response.getBody().asString());

		System.out.println("Response Headers: " + response.getHeaders());

		return petId;
	}

	@Test(priority = 2)
	public void findPetByInvalidId() {

		Response response = resourceHelper.findPetById(invalidPetId);

		Assert.assertEquals(response.getStatusCode(), 404);

		System.out.println("Response Body: " + response.getBody().asString());

		System.out.println("Response Headers: " + response.getHeaders());
	}

	@Test(priority = 3)
	public void updateAnExistingPet() {

		PetRequestPOJO petRequestPOJO = createRandomPetRequest();
		petRequestPOJO.setStatus(configReader.getUpdatePetStatus());
		petRequestPOJO.setName(configReader.getUpdatePetName());

		Response response = ResourceHelper.putUpdatePet(petRequestPOJO);

		PetResponsePOJO petResponsePOJO = ResourceHelper.putUpdatePet(petRequestPOJO).as(PetResponsePOJO.class);

		Assert.assertEquals(200, response.getStatusCode());

		Assert.assertEquals(petRequestPOJO.getName(), petResponsePOJO.getName());

		Assert.assertEquals(petRequestPOJO.getStatus(), petResponsePOJO.getStatus());

		System.out.println("Response Body: " + response.getBody().asString());

		response.prettyPrint();
	}

	@Test(priority = 4)
	public void testFindPetsByStatus() {

		String[] expectedStatuses = { "available", "pending", "sold" };

		Response response = resourceHelper.findPetsByStatus();

		Assert.assertEquals(response.getStatusCode(), 200);

		for (String status : expectedStatuses) {
			System.out.println(response.getBody().asString().contains(status));
		}
	}

	@Test(priority = 5)
	public void updatePetWithForm() {

		Response response = resourceHelper.updatePetWithFormData(findPetById(), updatePetName, updatePetStatus);

		Assert.assertEquals(200, response.getStatusCode());

		response.prettyPrint();
	}

	@Test(priority = 6)
	public void testUploadPetImage() {

		String filePath = configReader.getFileToUpload();
		File fileToUpload = new File(filePath);

		Response response = resourceHelper.uploadPetImage(findPetById(), fileToUpload);

		System.out.println("Response Body: " + response.getBody().asString());

		System.out.println("Response Headers: " + response.getHeaders());

		Assert.assertEquals(response.getStatusCode(), 200);

		response.prettyPrint();
	}

	@Test(priority = 7)
	public void deletePet() {

		Response response = resourceHelper.deletePet(findPetById());

		Assert.assertEquals(response.getStatusCode(), 200);

		System.out.println("Response Body: " + response.getBody().asString());

		System.out.println("Response headers: " + response.getHeaders());

		response.prettyPrint();
	}
	
	@Test(priority = 8)
	public void deletePetWithInvalidId() {

		Response response = resourceHelper.deletePet(invalidPetId);

		Assert.assertEquals(response.getStatusCode(), 404);

		System.out.println("Response Body: " + response.getBody().asString());

		System.out.println("Response headers: " + response.getHeaders());

		response.prettyPrint();
	}
	
	
}