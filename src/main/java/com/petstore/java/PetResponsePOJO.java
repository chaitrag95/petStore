package com.petstore.java;

import java.util.ArrayList;
import java.util.List;

public class PetResponsePOJO {

	private int id;
	private PetCategoryPOJO category;
	private String name;
	private ArrayList<String> photoUrls;
	private List<TagPOJO> tagPOJOs;
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PetCategoryPOJO getCategory() {
		return category;
	}

	public void setCategory(PetCategoryPOJO category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(ArrayList<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public List<TagPOJO> getTags() {
		return tagPOJOs;
	}

	public void setTags(List<TagPOJO> tagPOJOs) {
		this.tagPOJOs = tagPOJOs;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
