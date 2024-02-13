package com.petstore.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	private static Properties properties;

	static {
		properties = new Properties();
		try {
			FileInputStream fip = new FileInputStream(
					"C:\\Users\\HOME\\Desktop\\pet\\src\\main\\java\\com\\petstore\\resources\\config.properties");
			try {
				properties.load(fip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getBaseUri() {

		return properties.getProperty("baseUri");
	}

	public static String getApiKey() {
		return properties.getProperty("apiKey");
	}

	public static String getEndpoint() {
		return properties.getProperty("addPetAPIEndpoint");
	}

	public static String getUploadImageEndpoint() {
		return properties.getProperty("uploadImagePet");
	}

	public static String getFindByStatusEndpoint() {
		return properties.getProperty("findByStatusEndpoint");
	}

	public static String getFindPetById() {
		return properties.getProperty("findPetById");
	}

	public static String getUpdatePetAPIEndpoint() {
		return properties.getProperty("updatePetAPIEndpoint");
	}

	public static String getUpdatePetWithFormData() {
		return properties.getProperty("updatePetWithFormData");
	}

	public static String getDeletePet() {
		return properties.getProperty("deletePet");
	}

	public static String getPlaceOrderForPet() {
		return properties.getProperty("placeOrderForPet");
	}

	public static String getFindPurchaseOrderById() {
		return properties.getProperty("purchaseOrderById");
	}

	public static String getDeletePurchaseOrderById() {
		return properties.getProperty("deletePurchaseOrderById");
	}

	public static String getInventoriesByStatus() {
		return properties.getProperty("petInventoriesByStatus");
	}

	public static String getCreateUsersWithArray() {
		return properties.getProperty("createUsersWithArray");
	}

	public static String getCreateUsersWithList() {
		return properties.getProperty("createUsersWithList");
	}

	public static String getUserByUsername() {
		return properties.getProperty("getUserByUsername");
	}

	public static String getUpdateUser() {
		return properties.getProperty("updateUser");
	}

	public static String getDeleteUser() {
		return properties.getProperty("deleteUser");
	}

	public static String getUserLogin() {
		return properties.getProperty("userLogsIn");
	}

	public static String getUserLogOut() {
		return properties.getProperty("userLogsOut");
	}

	public static String getCreateUser() {
		return properties.getProperty("createUser");
	}

	public static String getPetId() {
		return properties.getProperty("petId");
	}

	public static String getPetName() {
		return properties.getProperty("petName");
	}

	public static String getStatus() {
		return properties.getProperty("status");
	}

	public static String getId() {
		return properties.getProperty("id");
	}

	public static String getUsername() {
		return properties.getProperty("username");
	}

	public static String getPassword() {
		return properties.getProperty("password");
	}

	public static String getUserUsername() {
		return properties.getProperty("userUsername");
	}

	public static String getUserPassword() {
		return properties.getProperty("userPassword");
	}

	public static String getUserId() {
		return properties.getProperty("userId");
	}

	public static String getUpdatePetName() {
		return properties.getProperty("updatePetName");
	}

	public static String getUpdatePetStatus() {
		return properties.getProperty("updateStaus");
	}

	public static String getFirstName() {
		return properties.getProperty("firstName");
	}

	public static String getFileToUpload() {
		return properties.getProperty("filePath");
	}

	public static String getOrderQuantity() {
		return properties.getProperty("orderQuantity");
	}

	public static String getOrderShippedDate() {
		return properties.getProperty("orderShipDate");
	}

	public static String getOrderStatus() {
		return properties.getProperty("orderStatus");
	}

	public static String getUpdateUserFirstName() {
		return properties.getProperty("updateUserFirstName");
	}

	public static String getUpdateUserLastName() {
		return properties.getProperty("updateUserLastName");
	}
	
	public static String getNewUser() {
		return properties.getProperty("newUsername");
	}
	
	public static String getNewUserID() {
		return properties.getProperty("newUserID");
	}
	
	public static String getInvalidPetId() {
		return properties.getProperty("invalidPetId");
	}
	
	public static String getInvalidOrderId() {
		return properties.getProperty("invalidOrderId");
	}
	
	public static String getInvalidUserId() {
		return properties.getProperty("invalidUserId");
	}
}
