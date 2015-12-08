package com.coextrix.vehicletracking.messageparserservice.model;

public class Vehicle {
	private String tagId;
	private String vehicleId;
	private String ownerName;
	private String ownerAddress;
	private String ownerPhoneNumber;

	public Vehicle(String tagId, String vehicleId, String ownerName,
			String ownerAddress, String ownerPhoneNumber) {
		super();
		this.tagId = tagId;
		this.vehicleId = vehicleId;
		this.ownerName = ownerName;
		this.ownerAddress = ownerAddress;
		this.ownerPhoneNumber = ownerPhoneNumber;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerAddress() {
		return ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	public String getOwnerPhoneNumber() {
		return ownerPhoneNumber;
	}

	public void setOwnerPhoneNumber(String ownerPhoneNumber) {
		this.ownerPhoneNumber = ownerPhoneNumber;
	}

}
