package com.petstore.ResourceHelper;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.petstore.java.OrderPOJO;
import com.petstore.java.PetRequestPOJO;
import com.petstore.java.UsersPOJO;
import com.petstore.resources.ConfigReader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ResourceHelper {
	
	static Response response;
	
	public static Response postAddPet(PetRequestPOJO petRequestPOJO) {
      return RestAssured.given()
                .baseUri(ConfigReader.getBaseUri())
                .contentType(ContentType.JSON)
                .header("api_key", ConfigReader.getApiKey())
                .body(petRequestPOJO)
                .when()
                .post(ConfigReader.getEndpoint());
    }
	
	public static Response uploadPetImage(int i, File file) {
        return RestAssured.given()
        		.baseUri(ConfigReader.getBaseUri())
                .header("api_key", ConfigReader.getApiKey())
                .contentType("multipart/form-data")
        		.multiPart("file", file)
        		.when()// Use multiPart() for file uploads
                .post(ConfigReader.getUploadImageEndpoint(),i);
          }
	
	public static Response findPetsByStatus() {
		Map<String, String> queryParams = new HashMap<>();
        queryParams.put("status", "available,pending,sold");

        // Make the request to find pets by status
        return RestAssured.given()
        		.baseUri(ConfigReader.getBaseUri())
                .header("api_key", ConfigReader.getApiKey())
                .queryParams(queryParams)
                .when()
                .get(ConfigReader.getFindByStatusEndpoint());
    }
	
	public static Response findPetById(long petId) {
		return RestAssured.given()
				.baseUri(ConfigReader.getBaseUri())
                .header("api_key", ConfigReader.getApiKey())
                .pathParam("petId", petId)
                .get(ConfigReader.getFindPetById());
    }
	
	public static Response putUpdatePet(PetRequestPOJO pet) {
	       return  RestAssured.given()
	             .baseUri(ConfigReader.getBaseUri())
	             .contentType(ContentType.JSON)
	             .header("api_key", ConfigReader.getApiKey())
	             .body(pet)
	             .when()
	             .put(ConfigReader.getUpdatePetAPIEndpoint());
	}
		
	public static Response updatePetWithFormData(int petId, String name, String status) {
		return RestAssured.given()
				 .baseUri(ConfigReader.getBaseUri())
                 .contentType(ContentType.URLENC)
                 .header("api_key", ConfigReader.getApiKey())
                 .formParam("id", petId)
                 .formParam("name", name)
                 .formParam("status", status)
                 .when()
                 .post(ConfigReader.getUpdatePetWithFormData(),petId);
    }
	
	public static Response deletePet(long petId) {
	      return  RestAssured.given()
	              .baseUri(ConfigReader.getBaseUri())
	              .header("api_key", ConfigReader.getApiKey())
	              .pathParam("petId", petId)
	              .when()
	              .delete(ConfigReader.getDeletePet());
	}
	
	public static Response orderPet(OrderPOJO orderPOJO) {
	       return  RestAssured.given()
	              .baseUri(ConfigReader.getBaseUri())
	              .contentType(ContentType.JSON)
	              .header("api_key", ConfigReader.getApiKey())
	              .body(orderPOJO)
	              .when()
	              .post(ConfigReader.getPlaceOrderForPet());
	}
	
	public static Response findPurchaseOrderById(int orderId) {
		   return RestAssured.given()
				  .baseUri(ConfigReader.getBaseUri())
                  .header("api_key", ConfigReader.getApiKey())
                  .pathParam("orderId", orderId)
                  .when()
                  .get(ConfigReader.getFindPurchaseOrderById());
    }
	
	public static Response getDeletePurchaseOrderById(int orderId) {
		    return RestAssured.given()
				   .baseUri(ConfigReader.getBaseUri())
				   .headers("api_key",ConfigReader.getApiKey())
				   .pathParam("orderId", orderId)
				   .when()
				   .delete(ConfigReader.getDeletePurchaseOrderById());
	}
	
	public static Response getInventoriesByStatus() {
		     return RestAssured.given()
				   .baseUri(ConfigReader.getBaseUri())
                   .header("api_key", ConfigReader.getApiKey())
                   .when()
                   .get(ConfigReader.getInventoriesByStatus());
    }
	
	public static Response getCreateUsersWithArray(UsersPOJO[] users) {
	          return  RestAssured.given()
	                .baseUri(ConfigReader.getBaseUri())
	                .contentType(ContentType.JSON)
	                .header("api_key", ConfigReader.getApiKey())
	                .body(users)
	                .when()
	                .post(ConfigReader.getCreateUsersWithArray());
	}
	
	public static Response getCreateUsersWithList(List<UsersPOJO> userList) {
	           return  RestAssured.given()
	                .baseUri(ConfigReader.getBaseUri())
	                .contentType(ContentType.JSON)
	                .header("api_key", ConfigReader.getApiKey())
	                .body(userList)
	                .when()
	                .post(ConfigReader.getCreateUsersWithList());
	}
	
	public static Response getUserByUsername(String username) {
		        return RestAssured.given()
				     .baseUri(ConfigReader.getBaseUri())
                     .header("api_key", ConfigReader.getApiKey())
                     .pathParam("username", username)
                     .when()
                     .get(ConfigReader.getUserByUsername());
    }
	
	public static Response getUpdateUser(UsersPOJO usersPOJO,String username) {
	             return  RestAssured.given()
	                  .baseUri(ConfigReader.getBaseUri())
	                  .contentType(ContentType.JSON)
	                  .header("api_key", ConfigReader.getApiKey())
	                  .pathParam("username", username)
	                  .body(usersPOJO)
	                  .when()
	                  .put(ConfigReader.getUpdateUser());
	}
	
	public static Response getDeleteUser(String username,String password,String newUser) {
		          return RestAssured.given()
				       .baseUri(ConfigReader.getBaseUri())
				       .headers("api_key",ConfigReader.getApiKey())	
				       .queryParams("username", username)
				       .queryParams("password", password)
				       .pathParam("username", newUser)
				       .when()
				       .delete(ConfigReader.getDeleteUser());
	}
	
	public static Response getLoginUser(String username,String password) {
		           return RestAssured.given()
				        .baseUri(ConfigReader.getBaseUri())
				        .headers("api_key",ConfigReader.getApiKey())
				        .queryParam("username", username)
				        .queryParam("password", password)
				        .when()
				        .get(ConfigReader.getUserLogin());
	}
	
	public static Response getLogoutUser(String username,String password) {
		            return RestAssured.given()
				         .baseUri(ConfigReader.getBaseUri())
				         .headers("api_key",ConfigReader.getApiKey())
				         .queryParams("username", username)
					     .queryParams("password", password)
				         .when()
				         .get(ConfigReader.getUserLogOut());
	}
	
	public static Response getCreateUser(String username,String password,UsersPOJO usersPOJO) {
	                return  RestAssured.given()
	                      .baseUri(ConfigReader.getBaseUri())
	                      .contentType(ContentType.JSON)
	                      .header("api_key", ConfigReader.getApiKey())
	                      .queryParams("username", username)
						  .queryParams("password", password)
	                      .body(usersPOJO)
	                      .when()
	                      .post(ConfigReader.getCreateUser());
	 }
}

